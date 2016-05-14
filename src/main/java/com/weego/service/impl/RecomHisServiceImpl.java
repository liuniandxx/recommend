package com.weego.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.weego.constent.Timedelta;
import com.weego.dao.CityDao;
import com.weego.dao.RecomHisDao;
import com.weego.dto.HisCardDto;
import com.weego.dto.RecomHisDto;
import com.weego.model.City;
import com.weego.model.HisContent;
import com.weego.model.RecomHistory;
import com.weego.service.RecomHisService;
import com.weego.util.DateUtil;
import com.weego.util.LoggerUtil;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ln
 */
@Service
public class RecomHisServiceImpl implements RecomHisService {
    @Resource
    private RecomHisDao recomHisDao;

    @Resource
    private CityDao cityDao;

    @Override
    public RecomHisDto queryHis(String userId, String cityId) {
        City city = cityDao.getCity(cityId);
        if (city == null) {
            return null;
        }

        RecomHisDto recomHisDto = new RecomHisDto();

        Date today = DateUtil.yyyyMMdd(new Date());
        Date dayBeforeYest = DateUtil.afterNDays(today, -2);

        List<RecomHistory> histories = recomHisDao.queryHistory(userId, cityId, dayBeforeYest, 3);
        histories = dupRuleFilter(histories);
        Map<String, List<HisCardDto>> map = getHisCards(histories);
        recomHisDto.setCityId(cityId);
        recomHisDto.setCityName(city.getCityName());
        recomHisDto.setToday(map.get("today"));
        recomHisDto.setYesterday(map.get("yesterday"));
        recomHisDto.setDayBeforeYest(map.get("dayBeforeYest"));
        return recomHisDto;
    }

    private List<RecomHistory> dupRuleFilter(List<RecomHistory> histories) {
        List<RecomHistory> rules = Lists.newArrayList();
        if(histories == null || histories.size() <= 0) {
            return rules;
        }
        Set<ObjectId> noDup = Sets.newHashSet();
        for(RecomHistory history : histories) {
            if(!noDup.contains(history.getRuleId())) {
                noDup.add(history.getRuleId());
                rules.add(history);
            }
        }
        return rules;
    }

    private Map<String, List<HisCardDto>> getHisCards(List<RecomHistory> histories){
        Map<String, List<HisCardDto>> map = new HashMap<String, List<HisCardDto>>() {
            {
                put("today", Lists.newArrayList());
                put("yesterday", Lists.newArrayList());
                put("dayBeforeYest", Lists.newArrayList());
            }
        };

        if(histories == null || histories.size() <= 0) {
            return map;
        }

        Date current = new Date();
        Set<HisContent> set = Sets.newHashSet();
        for(RecomHistory history : histories) {
            Date recomTime = history.getRecommendTime();
            List<HisContent> hisContents = history.getContents();
            if(hisContents == null || hisContents.size() <= 0) {
                continue;
            }
            String formatTime = parseRecomTime(current, recomTime);
            for(HisContent hisContent : hisContents) {
                if(!set.contains(hisContent)) {
                    set.add(hisContent);
                    List<HisCardDto> hisCards = map.get(whichDay(recomTime));
                    hisCards.add(getHisCard(hisContent, formatTime));
                }
            }
        }
        return map;
    }

    private HisCardDto getHisCard(HisContent his, String time) {
        HisCardDto hisCardDto = new HisCardDto();
        hisCardDto.setTime(time);
        hisCardDto.setTitle(his.getTitle());
        hisCardDto.setCoverImage(his.getCoverImage());
        hisCardDto.setRecomTitle(his.getRecomTitle());
        hisCardDto.setRecomDesc(his.getRecomDesc());
        hisCardDto.setRecomId(String.valueOf(his.getRecomId()));
        hisCardDto.setRecomType(his.getRecomType());
        return hisCardDto;
    }

    private String whichDay(Date reomTime) {
        Date today = DateUtil.yyyyMMdd(new Date());
        Date yesterday = DateUtil.afterNDays(today, -1);

        if(reomTime.compareTo(today) >= 0) {
            return "today";
        } else if(reomTime.compareTo(yesterday) >= 0) {
            return "yesterday";
        } else {
            return "dayBeforeYest";
        }
    }

    private String parseRecomTime(Date current, Date recomTime){
        LoggerUtil.logBiz("current = {}", current);

        long timedelta = (current.getTime() - recomTime.getTime()) / 1000;

        if(timedelta > Timedelta.FiveHour.getTimedelta()) {
            return DateUtil.formatColonHM(recomTime);
        } else if(timedelta > Timedelta.OneHour.getTimedelta()) {
            long cntHour = timedelta / Timedelta.OneHour.getTimedelta();
            return "" + cntHour + "小时前";
        } else if(timedelta > Timedelta.OneMinute.getTimedelta()) {
            long cntMinute = timedelta / Timedelta.OneMinute.getTimedelta();
            return "" + cntMinute + "分前";
        } else {
            return "" + timedelta + "秒前";
        }
    }
}
