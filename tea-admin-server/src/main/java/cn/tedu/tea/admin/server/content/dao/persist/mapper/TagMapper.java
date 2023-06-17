package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.TagStandardVO;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    TagStandardVO getStandardById(Long id);

    /**
     * 查询标签类别列表
     *
     * @return 标签类别列表
     */
    List<TagTypeListItemVO> listTagType();

    // name, parent_id, enable, sort
    // xx list(Long parentId);
}