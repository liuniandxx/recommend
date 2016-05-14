package com.weego.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.weego.model.RecomHistory;
import com.weego.util.DateUtil;
import com.weego.util.LoggerUtil;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ln
 */
@Repository
public class RecomHisDao {
    @Resource
    private Mongo mongo;
    private JacksonDBCollection<RecomHistory, String> coll;

    @PostConstruct
    private void init() {
        DBCollection collection = mongo.getCollection("recommendhistory");
        this.coll = JacksonDBCollection.wrap(collection, RecomHistory.class, String.class);
    }

    public List<RecomHistory> queryHistory(String userId, String cityId, Date date, int days) {

        LoggerUtil.logBiz("********** Recommend History Query ***************", null);
        LoggerUtil.logBiz("userId:", userId);
        LoggerUtil.logBiz("cityId:", cityId);

        Date nextDay = DateUtil.afterNDays(date, days);
        DBObject orderBy = new BasicDBObject();

        BasicDBObject query = new BasicDBObject();
        query.put("city._id", new ObjectId(cityId));
        query.put("user_id", userId);
        query.put("recommend_time", new BasicDBObject("$gte", date));
        query.put("recommend_time", new BasicDBObject("$lt", nextDay));
        orderBy.put("recommend_time", -1);
        return coll.find(query).sort(orderBy).toArray();
    }

    public void insertRecomHis(RecomHistory history) {
        coll.save(history);
    }
}
