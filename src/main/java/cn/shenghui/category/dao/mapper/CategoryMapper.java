package cn.shenghui.category.dao.mapper;

import cn.shenghui.category.dao.model.Category;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:36
 */
@Mapper
public interface CategoryMapper {

    /**
     * select category list
     * @param category
     * @return
     */
    List<Category> selectCategoryList(Category category);

    /**
     * create one category
     * @param category
     */
    void createCategory(Category category);

    /**
     * delete all the categories under it besides this one
     * @param category
     */
    void deleteCategory(Category category);

    /**
     * update one category
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * update its children
     * @param categories
     * @return
     */
    int updateCategoryChildren(@Param("categories")List<Category> categories);
}
