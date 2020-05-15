package org.psawesome.rsocketcrudclient.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.RSocketServerBootstrap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PostClientControllerTest {

    WebClient client;

    @BeforeEach
    void setUp() {
        client = WebClient.builder().baseUrl("localhost:8090/posts")
                .build();
    }

    @Test
    void testNotNullClient() {
        Assertions.assertNotNull(client);
    }

    @Test
    void findAll() {
        Flux<MyPost> mapMono = client.get().retrieve()
                .bodyToFlux(MyPost.class)
                ;
        MyPost myPost = new MyPost(1L, "post one in data.sql", "content of post one in data.sql");
        StepVerifier
                .create(mapMono)
                .recordWith(ArrayList::new)
                .expectNextCount(1)
                .consumeRecordedWith(
                        results ->
                                assertAll(
                                        () -> Assertions.assertTrue(results.contains(myPost))
                                )
                ).expectComplete()
                .verify();
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}