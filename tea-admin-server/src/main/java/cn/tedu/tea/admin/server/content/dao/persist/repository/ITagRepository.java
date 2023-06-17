package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;

/**
 * 处理标签数据的存储库接口
 *
 */
public interface ITagRepository {

    /**
     * 插入标签数据
     *
     */
    int insert(Tag tag);

    /**
     * 根据名称统计标签数量，通常用于检查名称是否被占用
     *
     */
    int countByName(String name);

    /**
     * 查询标签类别列表
     *
     */
    PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize);

}
