package cn.shenghui.controller;

import cn.shenghui.category.dao.model.Category;
import cn.shenghui.category.service.CategoryService;
import cn.shenghui.rest.request.CreateCategoryRequest;
import cn.shenghui.rest.response.CategoryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:23
 */
@RestController
@Api(value = "Category")
@RequestMapping("/category")
public class CategoryController {
    private int root = 100;
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "get all categories", notes = "statusCode = 0, failed; " +
            "statusCode = 1, success and return all categories")
    @GetMapping("/list")
    public List<Category> list(Category category) {
        List<Category> categoryList = categoryService.selectCategoryList(category);
        return categoryList;
    }

    @ApiOperation(value = "enter one product", notes = "statusCode = 0, failed; " +
            "statusCode = 1, success and return the category information")
    @PostMapping("/add")
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest){

        CategoryResponse response = new CategoryResponse();
        response.setParent_id(createCategoryRequest.getParent_id());
        response.setCategory_name(createCategoryRequest.getCategory_name());
        response.setStatusCode(1);
        return response;
    }
}
