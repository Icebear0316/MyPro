package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ITagRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.param.TagAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagUpdateInfoParam;
import cn.tedu.tea.admin.server.content.pojo.vo.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.TagStandardVO;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;
import cn.tedu.tea.admin.server.content.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
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

    @Override
    public void addNew(TagAddNewParam tagAddNewParam) {
        log.debug("开始处理【新增标签】的业务，参数：{}", tagAddNewParam);

        // TODO 可能需要添加其它业务规则

        String name = tagAddNewParam.getName();
        int count = tagRepository.countByName(name);
        if (count > 0) {
            String message = "新增标签失败，标签名称已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Tag tag = new Tag();
        BeanUtils.copyProperties(tagAddNewParam, tag);
        tag.setParentId(tagAddNewParam.getTypeId());
        tagRepository.insert(tag);
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【删除标签】的业务，参数：{}", id);

        TagStandardVO currentTag = tagRepository.getStandardById(id);
        if (currentTag == null || currentTag.getTypeId() == 0) {
            String message = "删除标签失败，尝试删除的标签数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        tagRepository.deleteById(id);
    }

    @Override
    public void updateInfoById(TagUpdateInfoParam tagUpdateInfoParam) {
        log.debug("开始处理【修改标签】的业务，参数：{}", tagUpdateInfoParam);

        Long id = tagUpdateInfoParam.getId();
        TagStandardVO currentTag = tagRepository.getStandardById(id);
        if (currentTag == null || currentTag.getTypeId() == 0) {
            String message = "修改标签失败，尝试修改的标签数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        String name = tagUpdateInfoParam.getName();
        int count = tagRepository.countByNameAndNotId(id, name);
        if (count > 0) {
            String message = "修改标签失败，标签名称已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Tag tag = new Tag();
        BeanUtils.copyProperties(tagUpdateInfoParam, tag);
        tag.setParentId(tagUpdateInfoParam.getTypeId());
        tagRepository.updateById(tag);
    }

    @Override
    public void setEnable(Long id) {
        log.debug("开始处理【启用标签】的业务，参数：{}", id);
        updateEnableById(id, 1);
    }

    @Override
    public void setDisable(Long id) {
        log.debug("开始处理【禁用标签】的业务，参数：{}", id);
        updateEnableById(id, 0);
    }

    @Override
    public TagStandardVO getStandardById(Long id) {
        log.debug("开始处理【根据ID查询标签】业务，参数：{}", id);
        TagStandardVO currentTag = tagRepository.getStandardById(id);
        if (currentTag == null || currentTag.getTypeId() == 0) {
            String message = "获取标签详情失败，尝试访问的标签数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return currentTag;
    }

    @Override
    public PageData<TagTypeListItemVO> listTagType(Integer pageNum) {
        log.debug("开始处理【查询标签类别列表】业务，页码：{}", pageNum);
        PageData<TagTypeListItemVO> pageData = tagRepository.listTagType(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询标签类别列表】业务，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageData<TagTypeListItemVO> pageData = tagRepository.listTagType(pageNum, pageSize);
        return pageData;
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum) {
        log.debug("开始处理【查询标签列表】的业务，页码：{}", pageNum);
        PageData<TagListItemVO> pageData = tagRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询标签列表】的业务，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageData<TagListItemVO> pageData = tagRepository.list(pageNum, pageSize);
        return pageData;
    }

    private void updateEnableById(Long id, Integer enable) {
        TagStandardVO currentTag = tagRepository.getStandardById(id);
        if (currentTag == null || currentTag.getTypeId() == 0) {
            String message = ENABLE_TEXT[enable] + "标签失败，尝试访问的标签数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentTag.getEnable() == enable) {
            String message = ENABLE_TEXT[enable] + "标签失败，标签已经处于此状态！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Tag tag = new Tag();
        tag.setId(id);
        tag.setEnable(enable);
        tagRepository.updateById(tag);
    }

}