package org.psawesome.rsocketcrudclient.http.router;

import lombok.RequiredArgsConstructor;
import org.psawesome.rsocketcrudclient.http.handler.MyPostHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class MyPostRouter {

  @Bean
  RouterFunction<?> postRouter(MyPostHandler myPostHandler) {
    return route(
            GET("/posts")
                    .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
            myPostHandler::findAll)
            .andRoute(
                    GET("/posts/{id}")
                            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                    myPostHandler::findById)
            .andRoute(POST("/posts")
            ,
                    myPostHandler::create)
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