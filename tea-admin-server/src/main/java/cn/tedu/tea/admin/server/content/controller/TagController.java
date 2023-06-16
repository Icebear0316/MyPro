package cn.tedu.tea.admin.server.content.controller;

import cn.tedu.tea.admin.server.common.web.JsonResult;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.service.ITagService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理标签相关请求的控制器类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/tags")
@Api(tags = "1.1. 内容管理-标签管理")
public class TagController {

    @Autowired
    private ITagService tagService;

    public TagController() {
        log.info("创建控制器对象：TagController");
    }

    @ApiOperation("新增标签类别")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@Validated TagTypeAddNewParam tagTypeAddNewParam) {
        log.debug("开始处理【新增标签类别】的请求，参数：{}", tagTypeAddNewParam);
        tagService.addNew(tagTypeAddNewParam);
        return JsonResult.ok();
    }

}