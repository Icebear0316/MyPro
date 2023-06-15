package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class TagServiceTests {

    @Autowired
    ITagService service;

    @Test
    @Sql(scripts = {"classpath:/sql/truncate_table.sql",
            "classpath:/sql/insert_data.sql"})
    @Sql(scripts = "classpath:/sql/truncate_table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addNew() {
        TagTypeAddNewParam tagTypeAddNewParam = new TagTypeAddNewParam();
        tagTypeAddNewParam.setName("茶叶标签");
        tagTypeAddNewParam.setEnable(1);
        tagTypeAddNewParam.setSort(88);

        try {
            service.addNew(tagTypeAddNewParam);
            System.out.println("添加数据完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

}
