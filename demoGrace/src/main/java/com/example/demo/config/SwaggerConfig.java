package com.example.demo.config;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/5/19 7:55 AM
 * @description
 */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    //http://localhost:8081/swagger-ui.html
//    //http://localhost:8081/doc.html
//
//    @Value("${swagger.enable}")
//    private boolean enable;
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("微服务接口调用文档")
//                .enable(enable)
//                .pathMapping("/")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
//                .paths(PathSelectors.any())
//                .build().apiInfo(new ApiInfoBuilder()
//                        .title("SpringBoot整合Swagger")
//                        .description("SpringBoot整合Swagger，详细信息......")
//                        .version("9.0")
//                        .contact(new Contact("啊啊啊啊","blog.csdn.net","aaa@gmail.com"))
//                        .license("The Apache License")
//                        .licenseUrl("http://www.baidu.com")
//                        .build());
//    }
//}
