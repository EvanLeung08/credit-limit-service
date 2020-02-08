package com.eshare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter
 *
 * COLA framework initialization is configured in {@link com.eshare.config.ColaConfig}
 *
 * @author Evan Leung
 */
@SpringBootApplication(scanBasePackages = {"com.eshare","com.alibaba.cola"})
@MapperScan("com.eshare.tunnel.database.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
