package com.weego.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.weego.model.RecomRule;
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
public class RecomRuleDao {
    @Resource
    private Mongo mongo;
    private JacksonDBCollection<RecomRule, String> coll;

    @PostConstruct
    private void init() {
        DBCollection collection = mongo.getCollection("recommendRules");
        this.coll = JacksonDBCollection.wrap(collection, RecomRule.class, String.class);
    }

    public List<RecomRule> queryAllRules() {
        LoggerUtil.logBiz("************ query all recommend rule********", null);
        return coll.find().toArray();
    }

    public RecomRule queryRule(String id) {
        LoggerUtil.logBiz("************* query recommend rule *******", null);
        LoggerUtil.logBiz("query recommend rule by id", id);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        return coll.findOne(query);
    }

    public List<RecomRule> queryRules(String cityId, String type) {
        LoggerUtil.logBiz("************ query recommend rule *********", null);
        LoggerUtil.logBiz("query recommend rule by cityId and type", null);
        LoggerUtil.logBiz("cityId: ", cityId);
        LoggerUtil.logBiz("type: ", type);

        BasicDBObject query = new BasicDBObject();
        query.put("city._id", cityId);
        query.put("type", type);

        List<RecomRule> recomRules = coll.find(query).toArray();
        return recomRules;
    }


    public List<RecomRule> queryRules(String cityId, String type, Date date) {
        LoggerUtil.logBiz("*********** query recommend rule ********", null);
        LoggerUtil.logBiz("cityId:", cityId);
        LoggerUtil.logBiz("type:", type);
        LoggerUtil.logBiz("Date:", date);

        String strDate = DateUtil.getDiscoveryTimeFormatter(date);
        int dayOfWeek = DateUtil.getDayOfWeek(date);
        int intHour = DateUtil.getHourOfDay(date);
        String dayOfHour = intHour < 10? "0" + intHour : String.valueOf(intHour);

        BasicDBObject query = new BasicDBObject();
        query.put("city._id", cityId);
        query.put("type", type);
        query.put("startDate", new BasicDBObject("$lte", strDate));
        query.put("endDate", new BasicDBObject("$gte", strDate));
        query.put("week", new BasicDBObject("$all", new Integer[]{dayOfWeek}));
        BasicDBObject timeQuery = new BasicDBObject();
        timeQuery.put("start_time", new BasicDBObject("$lte", dayOfHour));
        timeQuery.put("end_time", new BasicDBObject("$gt", dayOfHour));

        query.put("time_rules", new BasicDBObject("$elemMatch", timeQuery));
        return coll.find(query).toArray();
//
//        return coll.find(DBQuery.and(
//                    DBQuery.("city", DBQuery.is("_id", cityId))
////                    DBQuery.is("type", type),
////                    DBQuery.lessThanEquals("startDate", strDate),
////                    DBQuery.greaterThanEquals("endDate", strDate),
////                    DBQuery.all("week", dayOfWeek),
////                    DBQuery.elemMatch("time_rules", DBQuery.and(
////                            DBQuery.lessThanEquals("start_time", dayOfHour),
////                            DBQuery.greaterThan("end_time", dayOfHour)
//                    ))
//        )).toArray();
    }
}

