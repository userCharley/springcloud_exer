package com.charley.springcloud.alibaba.dao;

import com.charley.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    //新建订单
    public void create(Order order);

    //修改订单状态 从0改为1
    public void update(@Param("userId") Long userId, @Param("status") Integer status);
}
