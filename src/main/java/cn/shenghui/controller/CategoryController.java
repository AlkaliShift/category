package cn.shenghui.controller;

import cn.shenghui.category.dao.model.Category;
import cn.shenghui.category.service.CategoryService;
import cn.shenghui.rest.request.CreateCategoryRequest;
import cn.shenghui.rest.request.DeleteCategoryRequest;
import cn.shenghui.rest.response.CategoryBasicResponse;
import cn.shenghui.rest.response.CategoryListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
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
    public CategoryListResponse list(Category category) {
        CategoryListResponse response = new CategoryListResponse();
        if(category.getParentId() == 0){
            response.setStatusInfo(0,"Parent ID cannot be 0.");
            return response;
        }else{
            List<Category> categoryList = categoryService.selectCategoryList(category);
            response.setCategoryList(categoryList);
            response.setStatusInfo(1, "Success.");
            return response;
        }
    }

    @ApiOperation(value = "enter one category", notes = "statusCode = 0, message; " +
            "statusCode = 1, success")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public CategoryBasicResponse createCategory(@RequestBody @Validated CreateCategoryRequest createCategoryRequest){
        CategoryBasicResponse response = new CategoryBasicResponse();
        if(createCategoryRequest.getParentId() == 0){
            response.setStatusInfo(0,"Parent ID cannot be 0.");
            return response;
        }else{
            Category category = new Category();
            String categoryName = createCategoryRequest.getCategoryName();
            int parentId =createCategoryRequest.getParentId();
            String pAncestors = createCategoryRequest.getParentAncestors();
            category.setCategoryName(categoryName);
            category.setParentId(parentId);
            List<Category> existedCategory = categoryService.selectCategoryList(category);
            if(!ObjectUtils.isEmpty(existedCategory)){
                response.setStatusInfo(0, "Category '" + categoryName + "' has already been created.");
            }else{
                String ancestors = pAncestors + "," + parentId;
                category.setAncestors(ancestors);
                categoryService.createCategory(category);
                response.setStatusInfo(1, "Success.");
            }
            return response;
        }
    }

    @ApiOperation(value = "delete one category", notes = "statusCode = 0, message; " +
            "statusCode = 1, success")
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public CategoryBasicResponse deleteCategory(@RequestBody @Validated DeleteCategoryRequest deleteCategoryRequest){
        CategoryBasicResponse response = new CategoryBasicResponse();
        if(deleteCategoryRequest.getCategoryId() == 0){
            response.setStatusInfo(0, "Requests cannot be 0.");
        }else{
            Category category = new Category();
            category.setCategoryId(deleteCategoryRequest.getCategoryId());
            category.setAncestors(deleteCategoryRequest.getAncestors());
            List<Category> existedCategory = categoryService.selectCategoryList(category);
            if(ObjectUtils.isEmpty(existedCategory)){
                response.setStatusInfo(0, "Category not found.");
            }else{
                categoryService.deleteCategory(category);
                response.setStatusInfo(1, "Success.");
            }
        }
        return response;
    }
}
