package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.admin.server.content.pojo.vo.UpDownLogStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 处理顶踩日志数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Repository
public interface UpDownLogMapper extends BaseMapper<UpDownLog> {

    /**
     * 根据ID查询顶踩日志
     *
     * @param id 顶踩日志ID
     * @return 匹配的顶踩日志，如果没有匹配的数据，则返回null
     */
    UpDownLogStandardVO getStandardById(Long id);

}