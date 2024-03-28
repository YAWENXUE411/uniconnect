package com.ywm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@MapperScan(basePackages = {"com.ywm.mapper"})
@EnableCaching
public class FxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxApplication.class, args);
        System.out.println("http://localhost:9527/swagger-ui.html");
    }
}
