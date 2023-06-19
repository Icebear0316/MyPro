package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TagRepositoryTests {

    @Autowired
    ITagRepository repository;

    @Test
    void insert() {
        Tag tag = new Tag();
        tag.setName("茶叶标签");

        System.out.println("插入数据之前，参数：" + tag);
        int rows = repository.insert(tag);
        System.out.println("插入数据完成，受影响的行数：" + rows);
        System.out.println("插入数据之后，参数：" + tag);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        int rows = repository.deleteById(id);
        System.out.println("删除数据完成，受影响的行数：" + rows);
    }

    @Test
    void countByName() {
        String name = "茶叶标签";
        int count = repository.countByName(name);
        System.out.println("统计完成，结果：" + count);
    }

    @Test
    void getStandardById() {
        Long id = 1L;
        Object queryResult = repository.getStandardById(id);
        System.out.println("根据ID查询数据完成，查询结果：" + queryResult);
    }

    @Test
    void listTagType() {
        Integer pageNum = 2;
        Integer pageSize = 2;
        List<?> list = repository.listTagType(pageNum, pageSize).getList();
        System.out.println("根据列表数据完成，列表长度：" + list.size());
        for (Object item : list) {
            System.out.println("列表项：" + item);
        }
    }

    @Test
    void list() {
        Integer pageNum = 1;
        Integer pageSize = 10;
        List<?> list = repository.list(pageNum, pageSize).getList();
        System.out.println("根据列表数据完成，列表长度：" + list.size());
        for (Object item : list) {
            System.out.println("列表项：" + item);
        }
    }

}
