package com.charley.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.charley.springcloud.alibaba.dao"})
public class MybatisConfig {
}
