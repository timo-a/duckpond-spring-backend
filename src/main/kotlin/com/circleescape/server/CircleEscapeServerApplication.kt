package com.circleescape.server

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.headers.Header
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.event.EventListener

@SpringBootApplication
class CircleEscapeServerApplication {




    //private static final String BASEPATH = System.getenv("BASEPATH");
    //private static final String FRONTEND = System.getenv("FRONTEND");
    @Bean
    fun pondOpenApi(@Value("\${springdoc.version}") appVersion: String?): OpenAPI {
        val cs = Components().addSecuritySchemes(
            "basicScheme",
            SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
        )
        return OpenAPI().components(
            Components().addSecuritySchemes(
                "basicScheme",
                SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
            )
                .addParameters("myHeader1", Parameter().`in`("header").schema(StringSchema()).name("myHeader1"))
                .addHeaders("myHeader2", Header().description("myHeader2 header").schema(StringSchema()))
        )
            .info(
                Info()
                    .title("Pond Escape API")
                    .version(appVersion)
                    .description("This is a sample server Petstore server. You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.")
                    .termsOfService("http://swagger.io/terms/")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }

    @EventListener(ApplicationReadyEvent::class)
    fun doSomethingAfterStartup() {
        println("hello world, I have just started up")
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


}

fun main(args: Array<String>) {
    runApplication<CircleEscapeServerApplication>(*args)
}


