package cn.tedu.tea.admin.server.content.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.tea.admin.server.content.dao.persist.mapper")
public class MyBatisConfiguration {
}
