package com.charley.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    //正常访问，访问ok
    public String paymentInfo_OK(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        //程序暂停几秒
        int timeNumber = 3000;
        try{
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //int age = 10/0;
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: "
                + id + "\t" + "O(∩_∩)O哈哈~" + "  耗时(秒)：" + timeNumber;
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeOutHandler系统繁忙，请稍后再试,id: "
                + id + "\t" + "o(╥﹏╥)o";
    }


}
