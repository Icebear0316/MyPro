package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.UpDownLogMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IUpDownLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 处理顶踩日志数据的存储库实现类
 *
 */
@Slf4j
@Repository
public class UpDownDetailRepositoryImpl implements IUpDownLogRepository {

    @Autowired
    private UpDownLogMapper upDownLogMapper;

    public UpDownDetailRepositoryImpl() {
        log.info("创建存储库对象：UpDownDetailRepositoryImpl");
    }

}