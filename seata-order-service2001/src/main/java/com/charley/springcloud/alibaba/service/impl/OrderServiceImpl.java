package com.charley.springcloud.alibaba.service.impl;

import com.charley.springcloud.alibaba.dao.OrderDao;
import com.charley.springcloud.alibaba.domain.Order;
import com.charley.springcloud.alibaba.service.AccountService;
import com.charley.springcloud.alibaba.service.OrderService;
import com.charley.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp_create_order",rollbackFor = Exception.class)
    public void create(Order order) {
        System.out.println("开始创建订单");
        orderDao.create(order);
        System.out.println("订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        System.out.println("订单微服务开始调用库存，做扣减end");

        System.out.println("订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("订单微服务开始调用库存，做扣减end");

        //修改订单的状态  从0到1 1代表完成
        System.out.println("修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        System.out.println("修改订单状态结束");

        System.out.println("下订单结束：(*^▽^*)");
    }
}
