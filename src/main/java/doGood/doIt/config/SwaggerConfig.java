package doGood.doIt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false) // 기존적인 응답메세지 미사용
                .select()       // return ApiSelectorBuilder(화면 관리)
                .apis(RequestHandlerSelectors.basePackage("doGood.doIt.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
