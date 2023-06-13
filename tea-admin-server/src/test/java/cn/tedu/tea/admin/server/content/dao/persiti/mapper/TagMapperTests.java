package cn.tedu.tea.admin.server.content.dao.persiti.mapper;


import cn.tedu.tea.admin.server.content.dao.persist.mapper.TagMapper;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagMapperTests {

    @Autowired
    TagMapper mapper;

    @Test
    void insert() {
        Tag tag = new Tag();
        tag.setName("测试标签名称");

        mapper.insert(tag);
    }

}
