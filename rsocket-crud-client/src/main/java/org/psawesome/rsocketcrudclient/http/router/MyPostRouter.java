package org.psawesome.rsocketcrudclient.http.router;

import lombok.RequiredArgsConstructor;
import org.psawesome.rsocketcrudclient.http.handler.MyPostHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class MyPostRouter {

  @Bean
  RouterFunction<?> postRouter(MyPostHandler myPostHandler) {
    return route(GET("/posts").and(accept(MediaType.APPLICATION_JSON)),
            myPostHandler::findAll)
            .andRoute(GET("/posts/{id}").and(accept(MediaType.APPLICATION_JSON)),
                    myPostHandler::findById)
            .andRoute(POST("/posts"),
                    myPostHandler::save)
            .andRoute(PUT("/posts/{id}").and(accept(MediaType.APPLICATION_JSON)),
                    myPostHandler::update)
            .andRoute(DELETE("/posts/{id}").and(accept(MediaType.APPLICATION_JSON)),
                    myPostHandler::delete)
            ;

        /*
        RouterFunction<ServerResponse> postRoutes = RouterFunctions
                .route(RequestPredicates.method(HttpMethod.GET), myPostHandler::findAll)
                .andRoute(GET("/{id}"), myPostHandler::findById);

        return RouterFunctions
                .nest(
                        RequestPredicates.path("/posts"),
                        postRoutes
                );
        */
  }

}