package cn.shenghui.category.service;

import cn.shenghui.category.dao.mapper.CategoryMapper;
import cn.shenghui.category.dao.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Category createCategory(Category category){
        int row = categoryMapper.getRowNum();
        if(row == 0){

        }else{

        }
        return category;
    }
}
