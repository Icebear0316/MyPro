package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理类别数据的存储库接口
 *
 */
@Transactional
public interface ICategoryRepository {

    /**
     * 插入类别数据
     *
     * @param category 类别数据
     * @return 受影响的行数
     */
    int insert(Category category);

}
