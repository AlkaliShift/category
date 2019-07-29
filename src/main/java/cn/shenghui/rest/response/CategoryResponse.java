package cn.shenghui.rest.response;

import cn.shenghui.base.AbstractResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:22
 */
@Data
@ApiModel(value = "category response")
public class CategoryResponse extends AbstractResponse {
    /**
     * category id
     */
    int category_id;

    /**
     * parent id
     */
    int parent_id;

    /**
     * category name
     */
    String category_name;
}
