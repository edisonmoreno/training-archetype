package com.edisonmoreno.archetype.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/document/list"), handler::listenGetDocuments)
                .andRoute(POST("/api/document/reduces").and(accept(MediaType.MULTIPART_FORM_DATA)), handler::listenPOSTUseCase);
    }
}
