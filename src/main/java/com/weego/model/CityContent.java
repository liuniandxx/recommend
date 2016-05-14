package com.weego.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

/**
 * @author ln
 */
public class CityContent {
    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("cityname")
    private String cityname;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                    .append("_id", id)
                    .append("cityname", cityname)
                    .toString();
    }
}
