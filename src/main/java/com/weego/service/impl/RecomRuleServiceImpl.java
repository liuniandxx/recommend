package com.weego.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.weego.common.Response;
import com.weego.constent.BaseDataType;
import com.weego.constent.RecomRuleType;
import com.weego.dao.CityDao;
import com.weego.dao.RecomHisDao;
import com.weego.dao.RecomRuleDao;
import com.weego.dto.RecomCardDto;
import com.weego.dto.RecomDto;
import com.weego.model.*;
import com.weego.service.RecomRuleService;
import com.weego.util.DateUtil;
import com.weego.util.DistanceUtil;
import com.weego.util.LoggerUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ln
 */
@Service
public class RecomRuleServiceImpl implements RecomRuleService {

    @Resource
    private RecomRuleDao recomRuleDao;

    @Resource
    private RecomHisDao recomHisDao;

    @Resource
    private CityDao cityDao;

    @Value("${maxRecomSize}")
    private Integer maxRecomSize;

    @Override
    public RecomDto dynamicRecommend(String userId, String cityId, String location) {
        RecomDto recomDto = new RecomDto();
        City city = cityDao.getCity(cityId);
        recomDto.setCityId(cityId);
        recomDto.setCityName(city.getCityName());
        Timezone timezone = city.getTimezone();
        RecomRule rule = getRecomRule(userId, cityId, location, timezone);
        Date current = DateUtil.getTime(timezone.getRawOffset(), timezone.getDstOffset());
        recomDto.setTime(DateUtil.formatyyyyMMddHHmmss(current));
        if(rule == null) {
            recomDto.setTitle("");
            recomDto.setDesc("");
            recomDto.setData(Lists.newArrayList());
        } else {
            recomDto.setTitle(rule.getTitle());
            recomDto.setDesc(rule.getDesc());
            List<RecomContent> recomContents = rule.getContents();
            List<RecomCardDto> recomCards = getRecomcards(recomContents);
            recomDto.setData(recomCards);
            recomHisDao.insertRecomHis(getRecomHis(recomCards, rule, userId));
        }
        return recomDto;
    }

    private RecomHistory getRecomHis(List<RecomCardDto> recomContents, RecomRule rule, String userId) {
        RecomHistory history = new RecomHistory();
        history.setCity(rule.getCity());
        history.setRuleId(rule.getId());
        history.setRuleType(rule.getType());
        history.setUserId(userId);
        history.setRecommendTime(new Date());
        List<HisContent> hisContents = Lists.newArrayList();
        if(recomContents != null && recomContents.size() > 0) {
            for(RecomCardDto recom : recomContents) {
                HisContent his = new HisContent();
                his.setRecomId(new ObjectId(recom.getRecomId()));
                his.setRecomType(recom.getRecomType());
                his.setRecomTitle(recom.getRecomTitle());
                his.setRecomDesc(recom.getRecomDesc());
                his.setTitle(recom.getTitle());
                his.setCoverImage(recom.getCoverImage());
                hisContents.add(his);
            }
        }
        history.setContents(hisContents);
        return history;
    }

    private List<RecomCardDto> getRecomcards(List<RecomContent> contents) {
        List<RecomCardDto> cards = Lists.newArrayList();
        if(contents == null || contents.size() <= 0) {
            return cards;
        }
        List<BaseDataType> baseType = Arrays.asList(
                BaseDataType.ATTRACTION,
                BaseDataType.RESTAURANT,
                BaseDataType.SHOPPING,
                BaseDataType.AREA,
                BaseDataType.ACTIVITY,
                BaseDataType.PGC,
                BaseDataType.NEWS
        );
        int index = -1;
        while (!contents.isEmpty() && cards.size() < maxRecomSize) {
            index = (++index) % baseType.size();
            int pos = getRecomContent(contents, baseType.get(index));
            if(pos != -1) {
                cards.add(getRecomCard(contents.get(pos)));
                contents.remove(pos);
            }
        }
        return cards;
    }

    private int getRecomContent(List<RecomContent> contents, BaseDataType type) {
        if(contents == null || contents.size() <= 0) {
            return -1;
        }

        for(int i = 0; i < contents.size(); i++) {
            if(type.getType().equals(contents.get(i).getType())) {
                return i;
            }
        }
        return -1;
    }

    private RecomCardDto getRecomCard(RecomContent content) {
        RecomCardDto cardDto = new RecomCardDto();
        String type = content.getType();
        cardDto.setRecomId(content.getId().toString());
        cardDto.setCoverImage(content.getCoverImage());
        cardDto.setRecomTitle(content.getRecomTitle());
        cardDto.setRecomDesc(content.getRecomDesc());
        cardDto.setRecomType(content.getType());
        cardDto.setTitle(content.getTitle());
        if(BaseDataType.ATTRACTION.getType().equals(type) ||
                BaseDataType.RESTAURANT.getType().equals(type) ||
                BaseDataType.SHOPPING.getType().equals(type) ||
                BaseDataType.AREA.getType().equals(type)) {
            cardDto.setIntroduce(content.getIntroduce());
        } else if(BaseDataType.ACTIVITY.getType().equals(type)) {
            cardDto.setIntroduce(content.getOpenTime() + "-" + content.getCloseTime());
        } else if(BaseDataType.PGC.getType().equals(type)) {
            cardDto.setIntroduce(content.getIntroducation());
        } else if(BaseDataType.NEWS.getType().equals(type)) {
            cardDto.setIntroduce(content.getText());
        }
        return cardDto;
    }

    private RecomRule getRecomRule(String userId, String cityId, String location, Timezone timezone) {
        LoggerUtil.logBiz("**************获取推荐规则**************", null);
        LoggerUtil.logBiz("userId: ", userId);
        LoggerUtil.logBiz("cityId: ", cityId);

        RecomRule rule = null;
        Date today = DateUtil.yyyyMMdd(new Date());
        Date startTime = DateUtil.afterNDays(today, -2);
        LoggerUtil.logBiz("start time", startTime);

        List<RecomHistory> historyList = recomHisDao.queryHistory(userId, cityId, startTime, 3);
        Set<ObjectId> ruleSet = getHistoryRule(historyList);

        List<RecomRule> distanceRules = recomRuleDao.queryRules(cityId, RecomRuleType.DISTANCE.getType());
        Response<RecomRule> distanceRule = filterDistanceRule(distanceRules, location);
        LoggerUtil.logBiz("distanceRule: ", distanceRule.toString());

        Date date = DateUtil.getTime(timezone.getRawOffset(), timezone.getDstOffset());
        List<RecomRule> timeRules = recomRuleDao.queryRules(cityId, RecomRuleType.TIME.getType(), date);
        Response<RecomRule> timeRule = filterByTime(timeRules, ruleSet);

        if(distanceRule.getCode() == 1) {
            LoggerUtil.logBiz("******* find a distance rule ********", null);
            return distanceRule.getData();
        } else {
            if(timeRule.getCode() == 1) {
                LoggerUtil.logBiz("********** find a time rule *********", null);
                return timeRule.getData();
            }
        }

        LoggerUtil.logBiz("******* do not find a distance rule and a time rule ***********", null);
        LoggerUtil.logBiz("******* just return a matched time rule **********", null);
        return timeRule.getData();
    }

    private Set<ObjectId> getHistoryRule(List<RecomHistory> historyList) {
        Set<ObjectId> ruleSet = Sets.newHashSet();
        if(historyList == null || historyList.size() <= 0) {
            return ruleSet;
        }

        for(RecomHistory elem : historyList) {
            ruleSet.add(elem.getRuleId());
        }
        return ruleSet;
    }

    private Response<RecomRule> filterDistanceRule(List<RecomRule> distanceRules,
                                                   String location) {
        if(distanceRules == null || distanceRules.size() <= 0) {
            return Response.returnFail(null);
        }

        RecomRule rule = getPriorityRule(distanceRules, location);

        if(rule != null) {
            return Response.returnSuccess(rule);
        }
        return Response.returnFail(null);
    }

    private RecomRule getPriorityRule(List<RecomRule> distanceRules, String location) {
        String[] coordinate = location.split("[，,]");
        int distance = Integer.MAX_VALUE;
        RecomRule rule = null;

        for(RecomRule elem : distanceRules) {
            int curDistance = (int) DistanceUtil.getDistance(elem.getLatitude(), elem.getLongitude(), coordinate[0], coordinate[1]);
            if(distance > curDistance && curDistance <= Double.valueOf(elem.getRange())) {
                distance = curDistance;
                rule = elem;
            }
        }
        return rule;
    }

    private Response<RecomRule> filterByTime(List<RecomRule> timeRules,
                                             Set<ObjectId> ruleIds) {
        if(timeRules == null || timeRules.size() <= 0) {
            return Response.returnFail(null);
        }

        sortByTimePriority(timeRules);
        for(RecomRule rule : timeRules) {
            if(!ruleIds.contains(rule.getId())) {
                return Response.returnSuccess(rule);
            }
        }
        return Response.returnFail(timeRules.get(0));
    }

    private void sortByTimePriority(List<RecomRule> rules) {
        Ordering<RecomRule> orderByPro = new Ordering<RecomRule>() {
            @Override
            public int compare(RecomRule rule1, RecomRule rule2) {
                return getRulePriority(rule1).compareTo(getRulePriority(rule2));
            }
        };
        Collections.sort(rules, orderByPro);
    }

    private Integer getRulePriority(RecomRule rule) {
        String endDate = rule.getEndDate();

        Date current = DateUtil.yyyyMMdd(new Date());
        Integer days = DateUtil.daysBetween(current, DateUtil.getYYYYMMDD(endDate));
        Integer weeks = rule.getWeeks() == null? 0 : rule.getWeeks().size();
        Integer times = rule.getTimes() == null? 0 : rule.getTimes().size();
        return days + weeks + times;
    }
}
