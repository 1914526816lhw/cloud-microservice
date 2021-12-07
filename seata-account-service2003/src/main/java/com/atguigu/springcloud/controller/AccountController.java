package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decreaseAccount")
    public CommonResult decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decreaseAccount(userId, money);
        return new CommonResult(200, "扣减账户余额成功！");
    }
}
 
