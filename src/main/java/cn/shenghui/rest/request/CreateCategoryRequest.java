package cn.shenghui.rest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 14:13
 * category request
 */
@Data
@ApiModel(value = "create an category")
public class CreateCategoryRequest {
    @ApiModelProperty(value = "parent id", required = true)
    int parentId;

    @ApiModelProperty(value = "parents' ancestors", required = true)
    @NotEmpty(message = "Parents' ancestors are empty.")
    String parentAncestors;

    @ApiModelProperty(value = "category name", required = true)
    @NotEmpty(message = "Category name is empty.")
    String categoryName;
}
