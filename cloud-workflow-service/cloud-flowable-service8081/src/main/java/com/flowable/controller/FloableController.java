package com.flowable.controller;

import com.alibaba.fastjson.JSONObject;
import com.flowable.service.IFlowableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lihw
 * @className FloableController
 * @date 2022-04-29 09:22
 * @description
 */
@RestController
@RequestMapping("/flowable")
@Slf4j
public class FloableController {

    @Autowired
    private IFlowableService iFlowableService;

    @RequestMapping("/startProcess")
    public Object startProcess(@RequestBody Map<String, Object> map) {
        log.info(map.toString());
        return iFlowableService.startProcess(map);
    }
}
