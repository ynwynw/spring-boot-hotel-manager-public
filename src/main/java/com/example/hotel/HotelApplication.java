package com.example.hotel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootApplication
public class HotelApplication {

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {

        return registry -> {
            registry.addErrorPages(
                    new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                    new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500")
            );
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
        log.info("酒店管理系统 启动成功！");
    }

}
