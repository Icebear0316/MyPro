package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.vo.CategoryStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 处理类别数据的Mapper接口
 *
 *
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据ID查询类别
     *
     * @param id 类别ID
     * @return 匹配的类别，如果没有匹配的数据，则返回null
     */
    CategoryStandardVO getStandardById(Long id);

}