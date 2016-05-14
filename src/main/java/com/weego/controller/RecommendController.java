package com.weego.controller;

import com.weego.common.Response;
import com.weego.dto.RecomDto;
import com.weego.dto.RecomHisDto;
import com.weego.service.RecomHisService;
import com.weego.service.RecomRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ln
 */
@RestController
@RequestMapping("/recommend/v1")
public class RecommendController {

    @Resource
    private RecomRuleService recomRuleService;

    @Resource
    private RecomHisService recomHisService;

    @RequestMapping(value = "dynamic", method = RequestMethod.GET)
    @ResponseBody
    public Response<RecomDto> dynamicRecom(@RequestParam String userId,
                                          @RequestParam String cityId,
                                          @RequestParam String location) {
        RecomDto recomDto = recomRuleService.dynamicRecommend(userId, cityId, location);
        if(recomDto == null ||
                recomDto.getData() == null ||
                recomDto.getData().size() <= 0) {
            return Response.returnFail(recomDto);
        } else {
            return Response.returnSuccess(recomDto);
        }
    }

    @RequestMapping(value = "history", method = RequestMethod.GET)
    public Response<RecomHisDto> recomHistory(@RequestParam String userId,
                                              @RequestParam String cityId) {
        RecomHisDto recomHisDto = recomHisService.queryHis(userId, cityId);

        if(recomHisDto == null) {
            return Response.returnFail(recomHisDto);
        } else {
            return Response.returnSuccess(recomHisDto);
        }
    }
}
