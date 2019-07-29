package cn.shenghui.category.dao.mapper;

import cn.shenghui.category.dao.model.Category;
import org.mapstruct.Mapper;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:36
 */
@Mapper
public interface CategoryMapper {
    /**
     * get the number of rows
     * @return
     */
    int getRowNum();

    /**
     * clear category
     */
    void truncateCategory();

    /**
     * create one category
     * @param category
     */
    void createCategory(Category category);
}
