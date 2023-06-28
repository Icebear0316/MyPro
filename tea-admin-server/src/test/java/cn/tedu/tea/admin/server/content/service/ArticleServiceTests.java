package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.security.CurrentPrincipal;
import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ArticleServiceTests {

    @Autowired
    IArticleService service;

    @Test
    void addNew() {
        CurrentPrincipal currentPrincipal = new CurrentPrincipal();
        currentPrincipal.setId(1L);
        currentPrincipal.setUsername("root");

        String remoteAddr = "127.0.0.1";

        ArticleAddNewParam articleAddNewParam = new ArticleAddNewParam();
        articleAddNewParam.setAuthorId(1L);
        articleAddNewParam.setCategoryId(1L);
        articleAddNewParam.setTitle("大麦茶和麦芽茶的区别");

        try {
            service.addNew(currentPrincipal, remoteAddr, articleAddNewParam);
            System.out.println("添加数据完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

}
