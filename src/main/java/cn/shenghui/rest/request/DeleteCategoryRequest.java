package cn.shenghui.rest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/30 14:11
 */
@Data
@ApiModel(value = "delete category request")
public class DeleteCategoryRequest {

    @ApiModelProperty(value = "category id")
    int categoryId;
}
