package com.charley.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.charley.springcloud.dao"})
public class MybatisConfig {
}
