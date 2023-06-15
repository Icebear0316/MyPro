package cn.tedu.tea.admin.server.content.controller;


import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/content/tags")
public class TagController {

    @Autowired
    private ITagService tagService;

    public TagController() {
        log.info("创建控制器对象：TagController");
    }

    @RequestMapping("/add-new")
    public String addNew(TagTypeAddNewParam tagTypeAddNewParam) {
        log.debug("开始处理【新增标签类别】的请求，参数：{}", tagTypeAddNewParam);
        tagService.addNew(tagTypeAddNewParam);
        return "OK";
    }

}
