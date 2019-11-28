package com.program.email.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 UI configuration
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.program.email.controller")
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        // header中的ticket参数非必填，传空也可以
        ticketPar.name("token").description("user token").modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false).build();
        // 根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.program.email.controller"))
            .paths(PathSelectors.any()).build()
            .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("email Controller Api")
            .contact(new Contact("email", "", ""))
            .version("1.0").description("email Controller Server").build();
    }

}
