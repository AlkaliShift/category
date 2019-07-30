package cn.shenghui.category.service;

import cn.shenghui.category.dao.mapper.CategoryMapper;
import cn.shenghui.category.dao.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:37
 */
@Service
public class CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * select category list
     * @param category
     * @return
     */
    public List<Category> selectCategoryList(Category category) {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * create category
     * @param category
     */
    public void createCategory(Category category){
        categoryMapper.createCategory(category);
    }

    /**
     * delete category
     * @param category
     */
    public void deleteCategory(Category category) {
        categoryMapper.deleteCategory(category);
    }

    /**
     * update category
     * @param category
     * @return
     */
    public int updateCategory(Category category){
        return categoryMapper.updateCategory(category);
    }

    /**
     * update its children
     * @param category_id
     * @param ancestors
     */
    public void updateCategoryChildren(int category_id, String ancestors) {
        Category category = new Category();
        category.setParentId(category_id);
        List<Category> children = categoryMapper.selectCategoryList(category);
        if (children.size() > 0) {
            for (Category child : children) {
                child.setAncestors(ancestors + "," + category.getParentId());
                updateCategoryChildren(child.getCategoryId(), child.getAncestors());
            }
            categoryMapper.updateCategoryChildren(children);
        }
    }
}
