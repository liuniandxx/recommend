package com.weego.constent;

/**
 * @author:ln
 */
public enum Timedelta {
    OneMinute("一分钟", 60), OneHour("一小时", 60 *60), FiveHour("五小时", 5 * 60 * 60);

    private String desc;

    private Integer timedelta;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTimedelta() {
        return timedelta;
    }

    public void setTimedelta(Integer timedelta) {
        this.timedelta = timedelta;
    }

    Timedelta(String desc, Integer timedelta) {
        this.desc = desc;
        this.timedelta = timedelta;
    }

}
