package com.weego.constent;

/**
 * @author ln
 */
public enum RecomRuleType {
    TIME("1", "时间规则"), DISTANCE("2", "距离规则");

    RecomRuleType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private String type;

    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
