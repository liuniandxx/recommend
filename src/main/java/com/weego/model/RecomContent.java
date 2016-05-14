package com.weego.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

/**
 * @author ln
 */
public class RecomContent {

    @JsonProperty("id")
    private ObjectId id;  // poi id

    @JsonProperty("type")
    private String type;  // poi type

    @JsonProperty("recom_desc")
    private String recomDesc;

    @JsonProperty("recom_title")
    private  String recomTitle;

    @JsonProperty("title")
    private String title;

    @JsonProperty("coverImage")
    private String coverImage;

    @JsonProperty("text")
    private String text;

    @JsonProperty("short_introduce")
    private String introduce;


    @JsonProperty("introducation")
    private String introduction;

    @JsonProperty("open_time")
    private String openTime;

    @JsonProperty("close_time")
    private String closeTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecomDesc() {
        return recomDesc;
    }

    public void setRecomDesc(String recomDesc) {
        this.recomDesc = recomDesc;
    }

    public String getRecomTitle() {
        return recomTitle;
    }

    public void setRecomTitle(String recomTitle) {
        this.recomTitle = recomTitle;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroducation() {
        return introduction;
    }

    public void setIntroducation(String introducation) {
        this.introduction = introducation;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                    .append("id", id.toString())
                    .append("type", type)
                    .append("recomDesc", recomDesc)
                    .append("recomTitle", recomTitle)
                    .append("title", title)
                    .append("coverImage", coverImage)
                    .append("text", text)
                    .append("introduce", introduce)
                    .append("introduction", introduction)
                    .append("openTime", openTime)
                    .append("closeTime", closeTime)
                    .toString();
    }
}
