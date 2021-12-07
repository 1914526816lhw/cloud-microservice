package com.atguigu.springcloud.service.serviceRPC;

import com.atguigu.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName AccountService
 * @Description
 * @date 2021年12月06日 17:41
 */
@Component
@FeignClient(value = "seata-account-service")
public interface AccountService {

    @PostMapping(value = "/account/decreaseAccount")
    CommonResult decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
