package cn.shenghui.rest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/30 20:15
 */
@Data
@ApiModel(value = "update category request")
public class UpdateCategoryRequest {
    @ApiModelProperty(value = "category id")
    int categoryId;

    @ApiModelProperty(value = "parent id")
    int parentId;

    @ApiModelProperty(value = "target category id")
    int targetCategoryId;
}
