package com.eshare;

import org.h2.server.web.WebServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


/**
 * Spring Boot Starter
 *
 * COLA framework initialization is configured in {@link com.eshare.config.ColaConfig}
 *
 * @author Evan Leung
 */
@SpringBootApplication(scanBasePackages = {"com.eshare","com.alibaba.cola"})
@MapperScan("com.eshare.tunnel.database")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Enable h2 console
     * @return
     */
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/h2/*");
        return registration;
    }
}
