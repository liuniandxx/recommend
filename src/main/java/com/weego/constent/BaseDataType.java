package com.weego.constent;

/**
 * @author ln
 */
public enum BaseDataType {
    ATTRACTION("0", "景点地标"), RESTAURANT("1", "餐厅"), SHOPPING("2", "购物"),
    AREA("3", "购物圈"), ACTIVITY("4", "活动"), PGC("5", "pgc"), NEWS("6", "新闻");

    BaseDataType(String type, String desc) {
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
