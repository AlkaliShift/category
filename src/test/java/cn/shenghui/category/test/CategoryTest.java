package cn.shenghui.category.test;

import cn.shenghui.CategoryApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/31 09:17
 * category test
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CategoryApplication.class)
public class CategoryTest {
    private MockMvc mvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mvc = mockMvc;
    }

    @Test
    @Transactional
    @Rollback
    public void testAddCategory() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .post("/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"categoryName\": \"testName\",\n" +
                        "  \"parentId\": 100\n" +
                        "}")
        )
                .andExpect(jsonPath("$.statusCode").value(1))
                .andReturn();
    }

    @Test
    public void testListCategory() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .get("/category/list")
                .param("parentId", "100")
                .param("categoryId", "101")
        )
                .andExpect(jsonPath("$.statusCode").value(1))
                .andReturn();
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteCategory() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .post("/category/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"categoryId\": 105\n" +
                        "}")
        )
                .andExpect(jsonPath("$.statusCode").value(1))
                .andReturn();
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateCategory() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .post("/category/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"categoryId\": 105,\n" +
                        "  \"parentId\": 100,\n" +
                        "  \"targetCategoryId\": 102\n" +
                        "}")
        )
                .andExpect(jsonPath("$.statusCode").value(1))
                .andReturn();
    }
}
