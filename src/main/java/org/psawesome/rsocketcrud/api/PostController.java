package org.psawesome.rsocketcrud.api;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostRepository posts;

  @MessageMapping("posts.findAll")
  public Flux<MyPost> all() {
    return this.posts.findAll();
  }

  @MessageMapping("posts.findById.{id}")
  public Mono<MyPost> get(@DestinationVariable("id") Long id) {
    return this.posts.findById(id);
  }

  @MessageMapping("posts.save")
  public Mono<MyPost> save(@Payload MyPost post) {
    return this.posts.save(post);
  }

  @MessageMapping("posts.update.{id}")
  public Mono<MyPost> update(@Payload MyPost post, @DestinationVariable("id") Long id) {
    return this.posts.findById(id)
            .map(p -> {
              p.setTitle(post.getTitle());
              p.setContent(post.getContent());
              return p;
            })
            .flatMap(d -> this.posts.save(post));
  }

  @MessageMapping("posts.deleteById.{id}")
  public Mono<Void> delete(@DestinationVariable("id") Long id) {
    return this.posts.deleteById(id);
  }

}
