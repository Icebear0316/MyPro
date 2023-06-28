package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.CategoryStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理类别数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 批量插入类别数据
     *
     * @param categories 类别列表
     * @return 受影响的行数
     */
    int insertBatch(List<Category> categories);

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
     * 根据父级类别查询其子级类别列表
     *
     * @param parentId 父级类别的ID
     * @return 类别列表
     */
    List<CategoryListItemVO> listByParentId(Long parentId);

}
