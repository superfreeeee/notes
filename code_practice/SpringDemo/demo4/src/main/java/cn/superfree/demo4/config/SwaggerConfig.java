package cn.superfree.demo4.config;

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

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .pathMapping("/")
            .select()
            .apis(RequestHandlerSelectors.basePackage("cn.superfree.demo4.controller"))
            .paths(PathSelectors.any())
            .build().apiInfo(new ApiInfoBuilder()
                    .title("SpringDemo4 Swagger")
                    .description("Spring Boot 整合 Swagger2 自動化生成文檔")
                    .version("9.0")
                    .contact(new Contact("superfree", "cn.superfree", "181250003@smail.nju.edu.cn"))
                    .license("The Apache License")
                    .licenseUrl("http://www.javaboy.org")
                    .build());
  }
}
