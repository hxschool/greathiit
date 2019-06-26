package com.thinkgem.jeesite.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan("com.thinkgem.jeesite.modules")
public class SwaggerConfig {
	   
	   
	   @Bean
	    public Docket createAPI() {
	        return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).select().apis(RequestHandlerSelectors.basePackage("com.thinkgem.jeesite.modules.api.web"))
	                //过滤生成链接
	                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
	    }


	    /**
	     * 创建改API的基本信息（这些基本信息会展示在文档页面中）
	     * 访问地址： http://项目实际地址/swagger-ui.html
	     * @return
	     */
	    public ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("哈尔滨信息工程学院-API在线文档")
	                .description("哈尔滨信息工程学院始建于1995年，是经教育部批准设置的全日制普通本科高等学校，是国家示范性软件技术学院、全国高校毕业生就业工作先进单位。 学院两个校区分别坐落在高校林立的哈尔滨利民开发区学院路和哈东新区大学城。校园占地面积70万平方米，建筑面积20.4万平方米，教学仪器设备总值4280万元，图书68万余册，实现了校园内无线网络全覆盖，生均占有资源位于同类院校前列。")
	                .termsOfServiceUrl("http://www.greathiit.com")
	                .contact("773152@qq.com")
	                .version("1.0")
	                .build();
	    }
}
