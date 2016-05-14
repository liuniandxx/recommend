package com.weego.service;

import com.weego.dto.RecomDto;

/**
 * @author ln
 */
public interface RecomRuleService {
    RecomDto dynamicRecommend(String usetId, String cityId, String location);
}
