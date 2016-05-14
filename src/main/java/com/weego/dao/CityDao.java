package com.weego.dao;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.weego.model.City;
import com.weego.model.Timezone;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author ln
 */
@Repository
public class CityDao {
    @Resource
    private Mongo mongo;

    private MongoCollection<Document> coll;

    @PostConstruct
    private void init() {
        this.coll = mongo.getDbCollection("latestcity");
    }

    public City getCity(String cityId) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(cityId));
        FindIterable<Document> iterable = coll.find(query);

        List<City> cityList = Lists.newArrayList();
        for(Document elem : iterable) {
            City city = new City();
            city.setId(elem.getObjectId("_id"));
            city.setCityName(elem.getString("cityname"));
            Timezone timezone = new Timezone();
            Document cityTimeZone = (Document)elem.get("timezone");
            timezone.setDstOffset(cityTimeZone.getInteger("dstOffset"));
            timezone.setRawOffset(cityTimeZone.getInteger("rawOffset"));
            city.setTimezone(timezone);
            cityList.add(city);
        }
        if(cityList.size() > 0) {
            return cityList.get(0);
        }
        return null;
    }
}
