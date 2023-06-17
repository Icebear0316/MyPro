package cn.tedu.tea.admin.server.content.controller;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;
import cn.tedu.tea.admin.server.content.service.ITagService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 处理标签相关请求的控制器类
 *
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

    // 添加@RequestBody：接收对象参数
    // 不添加@RequestBody：接收FormData参数
    @ApiOperation("新增标签类别")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@Validated TagTypeAddNewParam tagTypeAddNewParam) {
        log.debug("开始处理【新增标签类别】的请求，参数：{}", tagTypeAddNewParam);
        tagService.addNew(tagTypeAddNewParam);
        return JsonResult.ok();
    }

    @ApiOperation("查询标签类别列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
        @ApiImplicitParam(name = "queryType", value = "查询类型，当需要查询全部数据时，此参数值应该是all")
    })
    @GetMapping("/tag-type/list")
    public JsonResult listTagType(Integer page, String queryType) {
        log.debug("开始处理【查询标签类别列表】请求，页码：{}", page);
        if (page == null) {
            page = 1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<TagTypeListItemVO> pageData ;
        if ("all".equals(queryType)) {
            pageData = tagService.listTagType(pageNum, Integer.MAX_VALUE);
        } else {
            pageData = tagService.listTagType(pageNum);
        }
        return JsonResult.ok(pageData);
    }

}