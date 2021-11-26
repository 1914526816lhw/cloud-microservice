package com.atguigu.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName MessageService
 * @Description
 * @date 2021年11月21日 23:12
 */
@Service
public class MessageService {

    @SentinelResource("message")
    public String getMessage(String message) {
        return message;
    }

}
