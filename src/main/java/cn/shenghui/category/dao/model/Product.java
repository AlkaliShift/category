package cn.shenghui.category.dao.model;

import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:15
 * Product
 */
@Data
public class Product {
    /**
     * product id
     */
    int product_id;

    /**
     * product name
     */
    String product_name;

    /**
     * product category
     */
    int product_category;

    /**
     * product quantity
     */
    int product_num;
}
