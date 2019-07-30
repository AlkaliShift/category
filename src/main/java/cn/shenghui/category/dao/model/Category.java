package cn.shenghui.category.dao.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:34
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * category id
     */
    private int categoryId;

    /**
     * parent id
     */
    private int parentId;

    /**
     * category name
     */
    private String categoryName;

    /**
     * delete flag
     * 0 means exist
     * 1 means deleted
     */
    private char delFlag;
}
