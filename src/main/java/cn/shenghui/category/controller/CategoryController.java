package cn.shenghui.category.controller;

import cn.shenghui.category.rest.request.CreateCategoryRequest;
import cn.shenghui.category.rest.response.CategoryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:23
 */
@RestController
@Api(value = "Category")
public class CategoryController {
    @ApiOperation(value = "enter one product", notes = "statusCode = 0, failed; " +
            "statusCode = 1, success and return the category information")
    @PostMapping("/createCategory")
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest){

        CategoryResponse response = new CategoryResponse();
        response.setParent_id(createCategoryRequest.getParent_id());
        response.setCategory_name(createCategoryRequest.getCategory_name());
        response.setStatusCode(1);
        return response;
    }
}
