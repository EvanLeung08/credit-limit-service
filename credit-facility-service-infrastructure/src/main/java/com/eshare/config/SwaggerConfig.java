package com.eshare.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.host}")
    private String swaggerHost;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshare.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("简单版消费金融额度服务")
                        .description("提供额度账户开通、额度状态管理、额度数值变更、额度注册、额度查询等操作")
                        .version("1.0")
                        .contact(new Contact("Evan Leung","http://www.evanshare.com","10856214@163.com"))
                        .license("The Apache License")
                        .licenseUrl("Even Leung")
                        .build());
    }
}