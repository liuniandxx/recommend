package com.weego.model;

import org.bson.types.ObjectId;


/**
 * @author ln
 */
public class City {
    private ObjectId id;

    private String cityName;

    private Timezone timezone;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
