package cn.shenghui.controller;

import cn.shenghui.category.dao.model.Category;
import cn.shenghui.category.service.CategoryService;
import cn.shenghui.rest.request.CreateCategoryRequest;
import cn.shenghui.rest.response.CategoryBasicResponse;
import cn.shenghui.rest.response.CategoryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
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
    @Transactional(rollbackFor = Exception.class)
    public CategoryBasicResponse createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        CategoryBasicResponse response = new CategoryBasicResponse();
        /**
         * check if the elements of createCategoryRequest are empty
         */
        if(createCategoryRequest.getCategory_name().isEmpty() || createCategoryRequest.getParent_id() == 0){
            response.setStatusInfo(0, "Incomplete request.");
        }else{
            Category category = new Category();
            String categoryName = createCategoryRequest.getCategory_name();
            category.setCategoryName(categoryName);
            category.setParentId(createCategoryRequest.getParent_id());
            List<Category> existCategory = categoryService.selectCategoryList(category);
            if(!ObjectUtils.isEmpty(existCategory)){
                response.setStatusInfo(0, "Category '" + categoryName + "' has already been created.");
            }else{
                categoryService.createCategory(category);
                response.setStatusInfo(1, "Success");
            }
        }
        return response;
    }
}
