package cn.tedu.tea.admin.server.content.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTests {

    @Autowired
    MockMvc mockMvc; // axios

    // RequestBuilder
    // MockMvc RequestBuilder s

    @Test
    @Sql(scripts = {"classpath:/sql/truncate_table.sql",
            "classpath:/sql/insert_data.sql"})
    @Sql(scripts = "classpath:/sql/truncate_table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addNew() throws Throwable {
        String url = "/content/tags/add-new";

        String name = "茶叶标签";
        String enable = "1";
        String sort = "88";

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .param("name", name)
                .param("enable", enable)
                .param("sort", sort)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

}