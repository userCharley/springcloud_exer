package com.charley.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA(){
        return "-----testA";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "-----testB";
    }

    @GetMapping(value = "/testD")
    public String testD(){
        //暂停几秒线程
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "-----testB";
    }

    @GetMapping(value = "/testE")
    public String testE(){
        System.out.println("测试异常数");
        int age = 10/0;
        return "-----test 测试异常数";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "-----testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-----deal_testHotKey,o(╥﹏╥)o";
    }
}
