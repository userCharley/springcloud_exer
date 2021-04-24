package com.charley.springcloud.alibaba.controller;

import com.charley.springcloud.alibaba.domain.CommonResult;
import com.charley.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StroageController {

    @Autowired
    private StorageService storageService;

    //扣减库存
    @GetMapping(value = "/storage/decrease")
    public CommonResult decrease(Long productId,Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"扣减库存成功");
    }
}
