package org.psawesome.rsocketcrudclient.http;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostClientController {

    private final RSocketRequester requester;

    @GetMapping("")
    public Flux<MyPost> findAll() {
        return this.requester.route("posts.findAll")
                .retrieveFlux(MyPost.class);
    }

    @GetMapping("{id}")
    public Mono<MyPost> findById(@PathVariable("id") Long id) {
        return this.requester.route(String.format("posts.findById.%d", id))
                .retrieveMono(MyPost.class);
    }

    @PostMapping("")
    public Mono<MyPost> save(@RequestBody MyPost post) {
        return this.requester.route("posts.save")
                .data(post)
                .retrieveMono(MyPost.class);
    }

    @PutMapping("{id}")
    public Mono<MyPost> update(@PathVariable Long id,
                               @RequestBody MyPost post) {
        return this.requester.route(String.format("posts.update.%d", id))
                .data(post)
                .retrieveMono(MyPost.class);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return this.requester.route(String.format("posts.delete.%d", id))
                .send();
    }
}

