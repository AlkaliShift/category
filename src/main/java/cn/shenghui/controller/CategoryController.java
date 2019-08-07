package cn.shenghui.controller;

import cn.shenghui.category.dao.model.Category;
import cn.shenghui.category.service.CategoryService;
import cn.shenghui.rest.request.CreateCategoryRequest;
import cn.shenghui.rest.request.DeleteCategoryRequest;
import cn.shenghui.rest.request.UpdateCategoryRequest;
import cn.shenghui.rest.response.CategoryBasicResponse;
import cn.shenghui.rest.response.CategoryListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "get category page")
    @GetMapping("")
    public ModelAndView getCategoryPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category");
        return mv;
    }

    @ApiOperation(value = "get all categories", notes = "statusCode = 0, failed; " +
            "statusCode = 1, success and return all categories")
    @GetMapping("/list")
    public CategoryListResponse list(Category category) {
        CategoryListResponse response = new CategoryListResponse();
        if (category.getParentId() == 0) {
            response.setStatusInfo(0, "Parent ID cannot be 0.");
            return response;
        } else {
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
            int parentId =createCategoryRequest.getParentId();
            Category parentCategory = new Category();
            parentCategory.setCategoryId(parentId);
            List<Category> parentCategoryList = categoryService.selectCategoryList(parentCategory);
            if(ObjectUtils.isEmpty(parentCategoryList)){
                response.setStatusInfo(0, "Wrong parent Category.");
            }else{
                String pAncestors = parentCategoryList.get(0).getAncestors();
                Category category = new Category();
                String categoryName = createCategoryRequest.getCategoryName();
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
            }
            return response;
        }
    }

    @ApiOperation(value = "delete one category", notes = "statusCode = 0, message; " +
            "statusCode = 1, success")
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public CategoryBasicResponse deleteCategory(@RequestBody @Validated DeleteCategoryRequest deleteCategoryRequest) {
        CategoryBasicResponse response = new CategoryBasicResponse();
        if (deleteCategoryRequest.getCategoryId() == 0) {
            response.setStatusInfo(0, "Requests cannot be 0.");
        } else {
            Category category = new Category();
            category.setCategoryId(deleteCategoryRequest.getCategoryId());
            List<Category> existedCategory = categoryService.selectCategoryList(category);
            if (ObjectUtils.isEmpty(existedCategory)) {
                response.setStatusInfo(0, "Category not found.");
            } else {
                String currentAncestors = existedCategory.get(0).getAncestors() + "," + category.getCategoryId();
                category.setAncestors(currentAncestors);
                categoryService.deleteCategory(category);
                response.setStatusInfo(1, " Delete success.");
            }
        }
        return response;
    }

    @ApiOperation(value = "update one category", notes = "statusCode = 0, message; " +
            "statusCode = 1, success")
    @PostMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public CategoryBasicResponse updateCategory(@RequestBody @Validated UpdateCategoryRequest updateCategoryRequest) {
        CategoryBasicResponse response = new CategoryBasicResponse();
        if (updateCategoryRequest.getCategoryId() == 0 ||
                updateCategoryRequest.getParentId() == 0 ||
                updateCategoryRequest.getTargetCategoryId() == 0) {
            response.setStatusInfo(0, "ID cannot be 0.");
        } else {
            Category category = new Category();
            category.setCategoryId(updateCategoryRequest.getCategoryId());
            List<Category> categoryList = categoryService.selectCategoryList(category);
            if (!ObjectUtils.isEmpty(categoryList)) {
                category = categoryList.get(0);

                Category targetCategory = new Category();

                targetCategory.setCategoryId(updateCategoryRequest.getTargetCategoryId());
                List<Category> targetCategoryList = categoryService.selectCategoryList(targetCategory);

                if (!ObjectUtils.isEmpty(targetCategoryList)) {
                    targetCategory = targetCategoryList.get(0);
                    category.setParentId(targetCategory.getCategoryId());
                    String ancestors = targetCategory.getAncestors() + "," + targetCategory.getCategoryId();
                    category.setAncestors(ancestors);
                    int result = categoryService.updateCategory(category);
                    categoryService.updateCategoryChildren(category.getCategoryId(), ancestors);
                    if (result == 1) {
                        response.setStatusInfo(1, "Update success.");
                    } else {
                        response.setStatusInfo(0, "Update failed.");
                    }
                } else {
                    response.setStatusInfo(0, "Target category not found.");
                }
            } else {
                response.setStatusInfo(0, "Category not found.");
            }
        }
        return response;
    }
}
