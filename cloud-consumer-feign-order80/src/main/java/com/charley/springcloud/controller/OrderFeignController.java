package com.charley.springcloud.controller;

import com.charley.springboot.entities.CommonResult;
import com.charley.springboot.entities.Payment;
import com.charley.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //OpenFeign,客户端默认等待1秒
        return paymentFeignService.paymentFeignTimeout();
    }
}
