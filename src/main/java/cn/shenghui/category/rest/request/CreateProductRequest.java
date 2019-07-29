package cn.shenghui.category.rest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:23
 */
@Data
@ApiModel(value = "enter one product")
public class CreateProductRequest {
    @ApiModelProperty(value = "product name")
    String product_name;

    @ApiModelProperty(value = "product category")
    int product_category;

    @ApiModelProperty(value = "product quantity")
    int product_num;
}
