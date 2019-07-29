package cn.shenghui.rest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:13
 * category request
 */
@Data
@ApiModel(value = "create an category")
public class CreateCategoryRequest {
    @ApiModelProperty(value = "parent id")
    int parent_id;

    @ApiModelProperty(value = "category name")
    String category_name;
}
