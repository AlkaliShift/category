package cn.shenghui.rest.response;

import cn.shenghui.base.AbstractResponse;
import cn.shenghui.category.dao.model.Category;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/30 15:00
 */
@Data
@ApiModel(value = "category list")
public class CategoryListResponse extends AbstractResponse {
    protected List<Category> categories;

    public void setCategoryList(List<Category> categories){
        this.setCategories(categories);
    }
}
