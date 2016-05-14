package com.weego.dao;

import com.weego.base.BaseTest;
import com.weego.model.City;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author ln
 */
public class CityDaoTest extends BaseTest {

    @Resource
    private CityDao cityDao;

    @Test
    public void testGetCity() {
        String cityId = "5698bec74fb72baf67000001";
        City city = cityDao.getCity(cityId);
        System.out.println(city);
    }
}
