package com.flowable.service;

import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author lihw
 * @className FlowableServiceImpl
 * @date 2022-04-29 09:19
 * @description
 */
@Service
public class FlowableServiceImpl implements IFlowableService {

    @Autowired
    private RuntimeService runtimeService;


    @Transactional
    @Override
    public Object startProcess(Map<String,Object> map) {
        return runtimeService.startProcessInstanceByKey("holidayRequest",map);
    }
}
