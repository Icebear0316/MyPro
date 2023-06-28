package cn.tedu.tea.admin.server.resource.controller;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import cn.tedu.tea.admin.server.resource.pojo.vo.UploadResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 处理文件上传的控制器类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/resources")
@Api(tags = "3.1. 文件上传模块")
public class ResourceController {

    @Value("${tea-store.upload.host}")
    private String host;
    @Value("${tea-store.upload.base-dir-name}")
    private String baseDirName;
    @Value("${tea-store.upload.root-dir-name}")
    private String uploadRootDirName;
    @Value("${tea-store.upload.article-image.max-size}")
    private Integer articleImageMaxSize;
    @Value("${tea-store.upload.article-image.types}")
    private List<String> articleImageValidTypes;

    private String articleImageDirName = "article-image/";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    public ResourceController() {
        log.debug("创建控制器类对象：ResourceController");
    }

    @PostMapping("/upload/image/article")
    @ApiOperation("上传文章图片")
    public JsonResult uploadProductImage(@RequestParam("file") MultipartFile multipartFile) throws Throwable {
        if (multipartFile == null || multipartFile.isEmpty()) {
            String message = "上传文章图片失败，请选择您要上传的文件！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_EMPTY, message);
        }

        long size = multipartFile.getSize();
        if (size > articleImageMaxSize * 1024 * 1024) {
            String message = "上传文章图片失败，不允许使用超过" + articleImageMaxSize + "MB的图片文件！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_EXCEED_MAX_SIZE, message);
        }

        String contentType = multipartFile.getContentType();
        if (!articleImageValidTypes.contains(contentType)) {
            String message = "上传文章图片失败，请使用以下类型的图片文件：" + articleImageValidTypes;
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_INVALID_TYPE, message);
        }

        String dirName = simpleDateFormat.format(new Date());
        File uploadBaseDir = new File(uploadRootDirName, baseDirName);
        File articleImageDir = new File(uploadBaseDir, articleImageDirName);
        File uploadDir = new File(articleImageDir, dirName);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String newFileName = UUID.randomUUID().toString();
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFullFileName = newFileName + suffix;
        File newFile = new File(uploadDir, newFullFileName);

        multipartFile.transferTo(newFile);

        String url = new StringBuilder()
                .append(host)
                .append(baseDirName)
                .append(articleImageDirName)
                .append(dirName)
                .append(newFullFileName)
                .toString();

        UploadResult uploadResult = new UploadResult();
        uploadResult.setUrl(url);
        uploadResult.setFileSize(size);
        uploadResult.setContentType(contentType);
        uploadResult.setFileName(newFullFileName);
        return JsonResult.ok(uploadResult);
    }

}
