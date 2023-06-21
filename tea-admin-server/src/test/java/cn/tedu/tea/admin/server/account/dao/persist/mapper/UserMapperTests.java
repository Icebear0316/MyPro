package cn.tedu.tea.admin.server.account.dao.persist.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void getLoginInfoByUsername() {
        String username = "root";
        Object queryResult = mapper.getLoginInfoByUsername(username);
        System.out.println("查询数据完成，查询结果：" + queryResult);
    }

}
