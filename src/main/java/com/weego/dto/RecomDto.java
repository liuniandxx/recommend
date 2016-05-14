package com.weego.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author ln
 */
public class RecomDto {

    private String cityId;

    private String cityName;

    private String title;

    private String desc;

    private String time;

    private List<RecomCardDto> data;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<RecomCardDto> getData() {
        return data;
    }

    public void setData(List<RecomCardDto> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                    .append("title", title)
                    .append("status", desc)
                    .append("cityId", cityId)
                    .append("cityName", cityName)
                    .append("data", data)
                    .toString();
    }
}
