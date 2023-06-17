package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.content.pojo.entity.Tag;

/**
 * 处理标签数据的存储库接口
 *
 */
public interface ITagRepository {

    /**
     * 插入标签数据
     *
     * @param tag 标签数据
     * @return 受影响的行数
     */
    int insert(Tag tag);

    /**
     * 根据名称统计标签数量，通常用于检查名称是否被占用
     *
     * @param name 标签名称
     * @return 匹配此标签名称的数据的数量
     */
    int countByName(String name);

}
