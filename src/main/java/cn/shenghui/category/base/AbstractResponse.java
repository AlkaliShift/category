package cn.shenghui.category.base;

import lombok.Data;

/**
 * @author shenghui
 * @version 1.0
 * @since 2019/7/29 10:17
 */
@Data
public abstract class AbstractResponse{
    protected int statusCode;

    protected String msg;

    public void setStatusInfo(int statusCode, String msg){
        this.setStatusCode(statusCode);
        this.setMsg(msg);
    }
}
