package cn.shenghui.rest.response;

import cn.shenghui.base.AbstractResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:24
 */
@Data
@ApiModel(value = "product information")
public class ProductResponse extends AbstractResponse {
    @ApiModelProperty(value = "product id")
    int product_id;

    @ApiModelProperty(value = "product name")
    String product_name;

    @ApiModelProperty(value = "product category")
    int product_category;

    @ApiModelProperty(value = "product quantity")
    int product_num;
}
