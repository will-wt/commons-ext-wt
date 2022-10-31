package com.will.commons.result;

/**
 * @author Will.WT
 * @date 2022/10/29 23:34
 */
public enum ResultCode {

    /**
     * 200-success
     */
    SUCCESS(200, "success"),

    ;

    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
