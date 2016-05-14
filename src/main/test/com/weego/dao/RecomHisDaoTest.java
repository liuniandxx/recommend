package com.weego.dao;

import com.weego.base.BaseTest;
import com.weego.model.RecomHistory;
import com.weego.util.DateUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ln
 */
public class RecomHisDaoTest extends BaseTest {
    @Resource
    private RecomHisDao recomHisDao;

    @Test
    public void getRecomHis() {
        String userId = "51a4834bb02bcb4853000001";
        String cityId = "516a35218902ca1936000005";

        Date date = new Date();
        date = DateUtil.yyyyMMdd(date);
        System.out.println(date);

        List<RecomHistory> list = recomHisDao.queryHistory(userId, cityId, date, 1);
        System.out.println(list);
    }
}
