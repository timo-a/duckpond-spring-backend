package com.circleescape.server;

import org.slf4j.Logger;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.circleescape.server.model.Game;
import com.circleescape.server.model.SessionDB;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.log4j.Log4j2;
@Log4j2
@SpringBootApplication
public class CircleEscapeServerApplication {
	
	//private static final String BASEPATH = System.getenv("BASEPATH");
	//private static final String FRONTEND = System.getenv("FRONTEND");

	@Bean
	public OpenAPI pondOpenApi(@Value("${springdoc.version}") String appVersion) {
		Components cs = new Components().addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"));
		return new OpenAPI().components(new Components().addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
		                                                .addParameters("myHeader1", new Parameter().in("header").schema(new StringSchema()).name("myHeader1"))
		                                                .addHeaders("myHeader2", new Header().description("myHeader2 header").schema(new StringSchema())))
		                  .info(new Info()
		                  .title("Pond Escape API")
		                  .version(appVersion)
		                  .description("This is a sample server Petstore server. You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.")
		                  .termsOfService("http://swagger.io/terms/")
		                  .license(new License().name("Apache 2.0").url("http://springdoc.org")));
				
				
	}

	public static void main(String[] args) {
		String basepath = "http://localhost:8080";
		log.info(String.format("api as json at %s/swagger-ui.html", basepath));
		log.info(String.format("api as json at %s/v3/api-docs", basepath));
		log.info(String.format("api as yaml at %s/v3/api-docs.yaml", basepath));
		SpringApplication.run(CircleEscapeServerApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    System.out.println("hello world, I have just started up");
	}

/*	@Bean
	public WebMvcConfigurer corsConfigurer() {
	   return new WebMvcConfigurer() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	    	  // doesn't work yet
	         registry.addMapping("/game").allowedOrigins(FRONTEND)
	                 //.addMapping("/game/planPolarSession").allowedOrigins(FRONTEND)
	                 ;
	      }
	   };
	}*/
	
	
	
	@Bean
	@SessionScope
	public Game getGame() {
		return new Game();
	}
	
	@Bean
	//@SessionScope
	public SessionDB getSessionDB() {
		return new SessionDB();
	}

}
