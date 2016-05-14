package com.weego.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.List;

/**
 * @author ln
 */
public class RecomRule {

    @Id
    private ObjectId id;

    @JsonProperty("isValid")
    private boolean isValid;

    @JsonProperty("type")
    private String type; // 0: 时间推送 1: 地理位置推送

    @JsonProperty("title")
    private String title; //规则名称

    @JsonProperty("desc")
    private String desc; //规则描述

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("range")
    private String range;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("week")
    private List<Integer> weeks;

    @JsonProperty("time_rules")
    private List<RecomTime> times;

    @JsonProperty("recom_content")
    private List<RecomContent> contents;

    @JsonProperty("one")
    private boolean one;

    @JsonProperty("two")
    private boolean two;

    @JsonProperty("three")
    private boolean three;

    @JsonProperty("four")
    private boolean four;

    @JsonProperty("five")
    private boolean five;

    @JsonProperty("six")
    private boolean six;

    @JsonProperty("seven")
    private boolean seven;

    @JsonProperty("eight")
    private boolean eight;

    @JsonProperty("city")
    private CityContent city;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Integer> weeks) {
        this.weeks = weeks;
    }

    public List<RecomTime> getTimes() {
        return times;
    }

    public void setTimes(List<RecomTime> times) {
        this.times = times;
    }

    public List<RecomContent> getContents() {
        return contents;
    }

    public void setContents(List<RecomContent> contents) {
        this.contents = contents;
    }

    public boolean isOne() {
        return one;
    }

    public void setOne(boolean one) {
        this.one = one;
    }

    public boolean isTwo() {
        return two;
    }

    public void setTwo(boolean two) {
        this.two = two;
    }

    public boolean isThree() {
        return three;
    }

    public void setThree(boolean three) {
        this.three = three;
    }

    public boolean isFour() {
        return four;
    }

    public void setFour(boolean four) {
        this.four = four;
    }

    public boolean isFive() {
        return five;
    }

    public void setFive(boolean five) {
        this.five = five;
    }

    public boolean isSix() {
        return six;
    }

    public void setSix(boolean six) {
        this.six = six;
    }

    public boolean isSeven() {
        return seven;
    }

    public void setSeven(boolean seven) {
        this.seven = seven;
    }

    public boolean isEight() {
        return eight;
    }

    public void setEight(boolean eight) {
        this.eight = eight;
    }

    public CityContent getCity() {
        return city;
    }

    public void setCity(CityContent city) {
        this.city = city;
    }
}
