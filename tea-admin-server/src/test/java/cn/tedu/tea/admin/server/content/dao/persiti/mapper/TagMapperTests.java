package cn.tedu.tea.admin.server.content.dao.persiti.mapper;


import cn.tedu.tea.admin.server.content.dao.persist.mapper.TagMapper;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TagMapperTests {

    @Autowired
    TagMapper mapper;

    @Test
    void insert() {
        Tag tag = new Tag();
        tag.setName("测试标签名称998");

        System.out.println("插入数据之前，参数：" + tag);
        int rows = mapper.insert(tag);
        System.out.println("插入数据成功，受影响的行数：" + rows);
        System.out.println("插入数据之后，参数：" + tag);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        int rows = mapper.deleteById(id);
        System.out.println("删除数据成功，受影响的行数：" + rows);
    }

    @Test
    void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        int rows = mapper.deleteByMap(map);
        System.out.println("删除数据成功，受影响的行数：" + rows);
    }

    @Test
    void delete() {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        int rows = mapper.delete(wrapper);
        System.out.println("删除数据成功，受影响的行数：" + rows);
    }
    @Test
    void deleteBatchIds() {
        List<Long> idList = new ArrayList<>();
        idList.add(4L);
        idList.add(5L);
        idList.add(6L);

        int rows = mapper.deleteBatchIds(idList);
        System.out.println("删除数据成功，受影响的行数：" + rows);
    }

    @Test
    void updateById() {
        Tag tag = new Tag();
        tag.setId(1L);
        // tag.setName("测试标签名称998");
        tag.setEnable(1);
        tag.setSort(198);

        int rows = mapper.updateById(tag);
        System.out.println("修改数据成功，受影响的行数：" + rows);
    }

    @Test
    void update() {
        Tag tag = new Tag();
        tag.setEnable(1);
        tag.setSort(197);

        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);

        int rows = mapper.update(tag, wrapper);
        System.out.println("修改数据成功，受影响的行数：" + rows);
    }

}
