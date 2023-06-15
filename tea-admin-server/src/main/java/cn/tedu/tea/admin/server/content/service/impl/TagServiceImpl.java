package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ITagRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理标签数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private ITagRepository tagRepository;

    public TagServiceImpl() {
        log.info("创建业务对象：TagServiceImpl");
    }

    @Override
    public void addNew(TagTypeAddNewParam tagTypeAddNewParam) {
        log.debug("开始处理【新增标签类别】的业务，参数：{}", tagTypeAddNewParam);
        String name = tagTypeAddNewParam.getName();
        int count = tagRepository.countByName(name);
        if (count > 0) {
            String message = "新增标签类别失败，标签类别名称已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Tag tag = new Tag();
        BeanUtils.copyProperties(tagTypeAddNewParam, tag);
        tag.setParentId(0L);
        tagRepository.insert(tag);
    }

}

// new ServiceException(1, "用户名不存在！");
// new ServiceException(2, "密码错误");
// new ServiceException(3, "账号被禁用");

// this.axios.post(url, params).then(() => {
//    let message = xxx;
//    let code = xxx;
//    if (code == 1) {
//        alert(message);
//    } else if (code == 2) {
//        alert(message);
//    }
// });










