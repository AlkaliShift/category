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
    int productId;

    /**
     * product name
     */
    String productName;

    /**
     * product category
     */
    int productCategory;

    /**
     * product quantity
     */
    int productNum;
}
