package cn.shenghui.category.dao.mapper;

import cn.shenghui.category.dao.model.Category;
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
     * clear category
     */
    void truncateCategory();

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


}
