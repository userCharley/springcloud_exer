package com.charley.springcloud.controller;

import com.charley.springboot.entities.CommonResult;
import com.charley.springboot.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"115552dada11d455555111fa"));
        hashMap.put(2L,new Payment(2L,"1555gt2dada11d455555111f"));
        hashMap.put(3L,new Payment(3L,"115552dada11d45dada5111f"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200,"from mysql,serverPort:" + serverPort,payment);
        return result;
    }
}
