package com.weego.service;

import com.weego.dto.RecomHisDto;

/**
 * @author ln
 */
public interface RecomHisService {
    RecomHisDto queryHis(String userId, String cityId);
}
