package pl.edu.pwsztar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket get(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("pl.edu.pwsztar.controller"))
                .build().apiInfo(createApiInfo());
    }

    private ApiInfo createApiInfo(){
        return new ApiInfo("Movies API",
                "API created for describing movies",
                "1.00",
                "http://localhost:63342/master/movies/frontend/movies.html",
                new Contact("Marcin", "", "macingoryl99@o2.pl"),
                "my own licence",
                "http://localhost:63342/master/movies/frontend/movies.html",
                Collections.emptyList()
                );
    }

    //http://localhost:8080/v2/api-docs
    //http://localhost:8080/swagger-ui.html
}
