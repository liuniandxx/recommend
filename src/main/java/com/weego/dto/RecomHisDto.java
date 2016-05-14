package com.weego.dto;

import java.util.List;

/**
 * @author ln
 */
public class RecomHisDto {
    private String cityId;

    private String cityName;

    private List<HisCardDto> today;

    private List<HisCardDto> yesterday;

    private List<HisCardDto> dayBeforeYest;

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

    public List<HisCardDto> getToday() {
        return today;
    }

    public void setToday(List<HisCardDto> today) {
        this.today = today;
    }

    public List<HisCardDto> getYesterday() {
        return yesterday;
    }

    public void setYesterday(List<HisCardDto> yesterday) {
        this.yesterday = yesterday;
    }

    public List<HisCardDto> getDayBeforeYest() {
        return dayBeforeYest;
    }

    public void setDayBeforeYest(List<HisCardDto> dayBeforeYest) {
        this.dayBeforeYest = dayBeforeYest;
    }
}
