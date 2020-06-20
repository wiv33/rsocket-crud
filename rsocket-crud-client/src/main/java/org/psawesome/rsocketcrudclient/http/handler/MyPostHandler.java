package org.psawesome.rsocketcrudclient.http.handler;

import lombok.RequiredArgsConstructor;
import org.psawesome.rsocketcrudclient.http.MyPost;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class MyPostHandler {

  private final RSocketRequester requester;

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.requester.route("posts.findAll")
                    .retrieveFlux(MyPost.class), MyPost.class);
  }

  public Mono<ServerResponse> findById(ServerRequest req) {
    return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.requester.route(String.format("posts.findById.%d", Long.parseLong(req.pathVariable("id")))
                    )
                            .retrieveMono(MyPost.class),
                    MyPost.class)
            .doOnError(throwable -> ServerResponse.notFound());
  }

  public Mono<ServerResponse> save(ServerRequest req) {
    return ok().body(this.requester.route("posts.save")
                    .data(req.bodyToMono(MyPost.class))
                    .retrieveMono(MyPost.class),
            MyPost.class);
  }


  public Mono<ServerResponse> update(ServerRequest req) {
    return ok().body(this.requester.route(String.format("posts.update.%s", req.pathVariable("id")))
                    .data(req.bodyToMono(MyPost.class))
                    .retrieveMono(MyPost.class),
            MyPost.class);
  }

  public Mono<ServerResponse> delete(ServerRequest req) {
    return ok().body(this.requester.route(String.format("posts.deleteById.%s", req.pathVariable("id")))
                    .retrieveFlux(MyPost.class),
            MyPost.class);
  }

}
