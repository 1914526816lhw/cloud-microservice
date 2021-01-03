package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName：PaymentServiceImpl
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/12/15 0:34
 * @version 1.0.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /*spring的注解*/
    /*@Autowired*/
    /*Java自带的注解*/
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
