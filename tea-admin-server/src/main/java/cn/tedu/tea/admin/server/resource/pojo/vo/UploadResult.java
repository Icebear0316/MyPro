package cn.tedu.tea.admin.server.resource.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传的结果
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class UploadResult implements Serializable {

    /**
     * 文件URL
     */
    private String url;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文档MIME类型
     */
    private String contentType;
    /**
     * 文件名
     */
    private String fileName;

}
