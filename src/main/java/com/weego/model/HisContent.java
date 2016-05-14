package com.weego.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

/**
 * @author ln
 */
public class HisContent {
    @JsonProperty("recommend_id")
    private ObjectId recomId;

    @JsonProperty("recommend_type")
    private String recomType;

    @JsonProperty("title")
    private String title;

    @JsonProperty("recommend_desc")
    private String recomDesc;

    @JsonProperty("recommend_title")
    private String recomTitle;

    @JsonProperty("coverImage")
    private String coverImage;

    public ObjectId getRecomId() {
        return recomId;
    }

    public void setRecomId(ObjectId recomId) {
        this.recomId = recomId;
    }

    public String getRecomType() {
        return recomType;
    }

    public void setRecomType(String recomType) {
        this.recomType = recomType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object obj) {
        HisContent content = (HisContent) obj;
        return this.recomId.equals(content.getRecomId()) &&
                this.recomType.equals(content.getRecomType());
    }
}
