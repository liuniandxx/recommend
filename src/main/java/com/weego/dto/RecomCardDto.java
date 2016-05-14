package com.weego.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author ln
 */
public class RecomCardDto {
    private String recomId;

    private String recomType;

    private String recomTitle;

    private String recomDesc;

    private String coverImage;

    private String introduce;

    private String title;


    public String getRecomId() {
        return recomId;
    }

    public void setRecomId(String recomId) {
        this.recomId = recomId;
    }

    public String getRecomType() {
        return recomType;
    }

    public void setRecomType(String recomType) {
        this.recomType = recomType;
    }

    public String getRecomTitle() {
        return recomTitle;
    }

    public void setRecomTitle(String recomTitle) {
        this.recomTitle = recomTitle;
    }

    public String getRecomDesc() {
        return recomDesc;
    }

    public void setRecomDesc(String recomDesc) {
        this.recomDesc = recomDesc;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                    .append("recomId", recomId)
                    .append("recomType", recomType)
                    .append("recomTitle", recomTitle)
                    .append("recomDesc", recomDesc)
                    .append("coverImage", coverImage)
                    .append("introduce", introduce)
                    .append("title", title)
                    .toString();
    }
}
