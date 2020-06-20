package org.psawesome.rsocketcrud.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {
  private final PostRepository repository;

  @MessageMapping("posts.findAll")
  public Flux<MyPost> all() {
    return this.repository.findAll()
            .log();
  }

  @MessageMapping("posts.findById.{id}")
  public Mono<MyPost> get(@DestinationVariable("id") Long id) {
    log.info("Destination FIND Id : {}", id);
    return this.repository.findById(id)
            .log();
  }

  @MessageMapping("posts.save")
  public Mono<MyPost> save(@Payload MyPost post) {
    log.info("Payload post data :{} ", post.toString());
    return this.repository.save(post)
            .log();
  }

  @MessageMapping("posts.update.{id}")
  public Mono<MyPost> update(@Payload MyPost post, @DestinationVariable("id") Long id) {
    log.info("Destination UPDATE Id : {}, Payload myPost : {}", id, post);
    return this.repository.findById(id)
            .map(p -> {
              p.setTitle(post.getTitle());
              p.setContent(post.getContent());
              return p;
            })
            .flatMap(d -> this.repository.save(post))
            .log();
  }

  @MessageMapping("posts.deleteById.{id}")
  public Mono<Void> delete(@DestinationVariable("id") Long id) {
    log.info("Destination DELETE Id : {}", id);

    return this.repository.deleteById(id)
            .log();
  }

}
