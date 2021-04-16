package com.charley.springcloud.controller;

import com.charley.springcloud.entities.CommonResult;
import com.charley.springcloud.entities.Payment;
import com.charley.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    //插入数据
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功，serverport：" + serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    //根据id查询数据
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            return new CommonResult(200,"查询成功，serverport：" + serverPort,payment);
        }else{
            return new CommonResult(400,"查询失败,没有对应记录",null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
