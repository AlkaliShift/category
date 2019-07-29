package cn.shenghui.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 21:05
 * Ztree
 */
@Data
public class Ztree implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * node id
     */
    private long id;

    /**
     * parent id
     */
    private long pId;

    /**
     * node name
     */
    private String name;

    /**
     * node title
     */
    private String title;

    /**
     * if checked
     */
    private boolean checked = false;

    /**
     * if open
     */
    private boolean open = false;
}
