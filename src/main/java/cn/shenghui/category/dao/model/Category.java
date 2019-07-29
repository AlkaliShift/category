package cn.shenghui.category.dao.model;

import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:34
 */
@Data
public class Category {

    /**
     * category id
     */
    int category_id;

    /**
     * category name
     */
    String category_name;

    /**
     * parent id
     */
    int parent_id;
}
