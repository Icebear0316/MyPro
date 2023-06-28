package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.CategoryStandardVO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 处理类别数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface ICategoryRepository {

    /**
     * 插入类别数据
     *
     * @param category 类别数据
     * @return 受影响的行数
     */
    int insert(Category category);

    /**
     * 批量插入类别数据
     *
     * @param categoryList 类别列表
     * @return 受影响的行数
     */
    int insertBatch(List<Category> categoryList);

    /**
     * 根据id删除类别数据
     *
     * @param id 类别ID
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据若干个ID批量删除类别数据
     *
     * @param idList 若干个类别ID的数组
     * @return 受影响的行数
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根据id修改类别数据
     *
     * @param category 封装了类别ID和新数据的对象
     * @return 受影响的行数
     */
    int update(Category category);

    /**
     * 统计类别表中的数据的数量
     *
     * @return 类别表中的数据的数量
     */
    int count();

    /**
     * 根据类别名称统计当前表中类别数据的数量
     *
     * @param name 类别名称
     * @return 当前表中匹配名称的类别数据的数量
     */
    int countByName(String name);

    /**
     * 统计当前表中非此类别id的匹配名称的类别数据的数量
     *
     * @param id   当前类别ID
     * @param name 类别名称
     * @return 当前表中非此类别id的匹配名称的类别数据的数量
     */
    int countByNameAndNotId(@Param("id") Long id, @Param("name") String name);

    /**
     * 根据父级类别，统计其子级类别的数量
     *
     * @param parentId 父级类别的ID
     * @return 此类别的子级类别的数量
     */
    int countByParentId(Long parentId);

    /**
     * 根据id查询类别数据详情
     *
     * @param id 类别ID
     * @return 匹配的类别数据详情，如果没有匹配的数据，则返回null
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 查询类别数据列表
     *
     * @return 类别数据列表
     */
    List<CategoryListItemVO> list();

    /**
     * 查询类别数据列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 类别数据列表
     */
    PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根据父级类别查询其子级类别列表
     *
     * @param parentId 父级类别的ID
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 类别列表
     */
    PageData<CategoryListItemVO> listByParentId(Long parentId, Integer pageNum, Integer pageSize);

}
