package com.weego.dao;

import com.weego.base.BaseTest;
import com.weego.model.RecomRule;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ln
 */
public class RecomRuleDaoTest extends BaseTest{

    @Resource
    private RecomRuleDao recomRuleDao;

    @Test
    public void testGetRuleById() {
        String id = "572ac754ebb4cf017000001c";

        RecomRule rule = recomRuleDao.queryRule(id);
        System.out.println(rule);
    }

    @Test
    public void testQueryByCityIdAndType() {
        String cityId = "516a35218902ca1936000005";
        String type = "1";

        List<RecomRule> rules = recomRuleDao.queryRules(cityId, type);
        System.out.println(rules);
    }

    @Test
    public void testQueryRules() {
        String cityId = "516a35218902ca1936000005";
        String type = "1";

        Date date = new DateTime(2016, 6, 15, 1, 10, 0,0).toDate();

        List<RecomRule> rules = recomRuleDao.queryRules(cityId, type, date);
        System.out.println(rules);
    }

    @Test
    public void testQueryAllRules() {
        List<RecomRule> rules = recomRuleDao.queryAllRules();
        System.out.println(rules);
    }


 }
