package com.weego.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.Date;
import java.util.List;

/**
 * @author ln
 */
public class RecomHistory {

    @Id
    private ObjectId id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("city")
    private CityContent city;

    @JsonProperty("rule_id")
    private ObjectId ruleId;

    @JsonProperty("rule_type")
    private String ruleType;

    @JsonProperty("recommend_time")
    private Date recommendTime;

    @JsonProperty("recommend_content")
    private List<HisContent> contents;

    @JsonProperty("coverImage")
    private String coverImage;

    public List<HisContent> getContents() {
        return contents;
    }

    public void setContents(List<HisContent> contents) {
        this.contents = contents;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CityContent getCity() {
        return city;
    }

    public void setCity(CityContent city) {
        this.city = city;
    }

    public ObjectId getRuleId() {
        return ruleId;
    }

    public void setRuleId(ObjectId ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
