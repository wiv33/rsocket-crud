package org.psawesome.rsocketcrudclient.http.handler;

import lombok.RequiredArgsConstructor;
import org.psawesome.rsocketcrudclient.http.MyPost;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MyPostHandler {

  private final RSocketRequester requester;

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.requester.route("posts.findAll")
                    .retrieveFlux(MyPost.class), MyPost.class);
  }

  public Mono<ServerResponse> findById(ServerRequest request) {
    return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.requester.route(String.format("posts.findById.%d", Long.parseLong(request.pathVariable("id"))))
                    .retrieveMono(MyPost.class), MyPost.class)
            .doOnError(throwable -> ServerResponse.notFound());
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    // TODO create logic
    return Mono.empty();
  }



}
