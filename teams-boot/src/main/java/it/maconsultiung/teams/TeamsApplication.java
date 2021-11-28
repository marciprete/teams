package it.maconsultiung.teams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {
        "it.maconsulting.teams.presentation",
        "it.maconsulting.teams.application",
        "it.maconsulting.teams.domain",
        "it.maconsulting.teams.persistence"})
@EntityScan("it.maconsulting.teams.persistence.jpa.entity")
@EnableJpaRepositories("it.maconsulting.teams.persistence.jpa.repository")
public class TeamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamsApplication.class, args);
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("it.maconsulting.teams.presentation.controller"))
                .paths(PathSelectors.any())
                .build()
                ;
    }
}
