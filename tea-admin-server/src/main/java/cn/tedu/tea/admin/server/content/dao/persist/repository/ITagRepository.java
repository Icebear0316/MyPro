package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.TagStandardVO;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;

/**
 * 处理标签数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 1.0
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
     * 根据ID删除标签数据
     *
     * @param id 尝试删除的标签的ID
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据ID修改标签数据
     *
     * @param tag 封装了被修改数据的ID和新数据的对象
     * @return 受影响的行数
     */
    int updateById(Tag tag);

    /**
     * 根据parentId修改启用状态
     *
     * @param parentId 标签类别ID
     * @param enable   新的启用状态
     * @return 受影响的行数
     */
    int updateEnableByParentId(Long parentId, Integer enable);

    /**
     * 根据名称统计标签数量，通常用于检查名称是否被占用
     *
     * @param name 标签名称
     * @return 匹配此标签名称的数据的数量
     */
    int countByName(String name);

    /**
     * 统计匹配名称查不匹配ID的标签数量，通常用于修改数据时判断名称是否被占用
     *
     * @param id   标签ID
     * @param name 标签数量
     * @return 匹配的标签的数量
     */
    int countByNameAndNotId(Long id, String name);

    /**
     * 根据ID查询标签
     *
     * @param id 标签ID
     * @return 匹配的标签，如果没有匹配的数据，则返回null
     */
    TagStandardVO getStandardById(Long id);

    /**
     * 查询标签类别列表
     *
     * @param pageNum  页码
     * @param pageSize 每条记录数
     * @return 标签类别列表的分页数据
     */
    PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize);

    /**
     * 查询标签列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 标签列表
     */
    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);

}
