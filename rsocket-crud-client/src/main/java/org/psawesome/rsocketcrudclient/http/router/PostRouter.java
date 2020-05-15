package org.psawesome.rsocketcrudclient.http.router;

import lombok.RequiredArgsConstructor;
import org.psawesome.rsocketcrudclient.http.MyPost;
import org.psawesome.rsocketcrudclient.http.handler.PostHandler;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class PostRouter {

    @Bean
    RouterFunction<?> postRouter(PostHandler postHandler) {
/*
        return route(
                    GET("/posts")
                            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                    req -> postHandler.findAll())
                ;
*/

        RouterFunction<ServerResponse> postRoutes = RouterFunctions
                .route(RequestPredicates.method(HttpMethod.GET), postHandler::findAll)
                .andRoute(GET("/{id}"), postHandler::findById);

        return RouterFunctions
                .nest(
                        RequestPredicates.path("/posts"),
                        postRoutes
                );
    }

}