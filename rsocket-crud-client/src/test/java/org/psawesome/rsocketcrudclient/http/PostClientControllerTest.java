package org.psawesome.rsocketcrudclient.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(properties = "application.yml")
class PostClientControllerTest {

  WebClient client;
  WebTestClient testClient;

  WebTestClient routeClient;


  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:8080/posts";
    System.setProperty("com.couchbase.client.core.deps.io.netty.noUnsafe", "true");

    client = WebClient
            .builder()
            .baseUrl(baseUrl)
            .build();

    testClient = WebTestClient
            .bindToServer()
            .baseUrl(baseUrl)
            .build();

//    routeClient = WebTestClient
//            .bindToRouterFunction(myPostRouter.postRouter())
//            .build();
  }

  @Test
  void testNotNullClient() {
    Assertions.assertNotNull(client);
  }

  @Test
  void testNotNullWebTestClient() {
    Assertions.assertNotNull(testClient);
  }

  @Test
  @DisplayName("Posts 전체 검색")
  void findAll() {
    Flux<MyPost> mapMono = client.get()
            .retrieve()
            .bodyToFlux(MyPost.class);
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
            )
            .thenCancel()
            .verify();
  }

  @Test
  @DisplayName("posts 검색")
  void findById() {
    testClient.get()
            .uri("/{id}", 1)
            .exchange()
            .expectBody(MyPost.class)
            .consumeWith(res ->
                    assertAll(
                            () -> assertNotNull(res.getResponseBody()),
                            () -> assertEquals(1L, Objects.requireNonNull(res.getResponseBody()).getId()),
                            () -> assertEquals("post one in data.sql", res.getResponseBody().getTitle()),
                            () -> assertEquals("content of post one in data.sql", res.getResponseBody().getContent())
                    )
            );
  }
  @Test
  @DisplayName("posts 검색 - RouterFunctionClient")
  void findByIdRouterTestClient() {
    routeClient.get()
            .uri("/{id}", 1)
            .exchange()
            .expectBody(MyPost.class)
            .consumeWith(res ->
                    assertAll(
                            () -> assertNotNull(res.getResponseBody()),
                            () -> assertEquals(1L, Objects.requireNonNull(res.getResponseBody()).getId()),
                            () -> assertEquals("post one in data.sql", res.getResponseBody().getTitle()),
                            () -> assertEquals("content of post one in data.sql", res.getResponseBody().getContent())
                    )
            );
  }

  @Test
  @DisplayName("저장")
  void save() {
    StepVerifier.create(client.post()
//            .uri("/posts")
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(MyPost.builder()
                            .title("ps")
                            .content("awesome")
                            .build()),
                    MyPost.class)
            .retrieve()
            .bodyToMono(MyPost.class)
    )
            .expectNextCount(1L)
            .expectNext(MyPost.builder()
                    .id(2L)
                    .title("ps")
                    .content("awesome")
                    .build()
            )
//            .recordWith(ArrayList::new)
//            .consumeNextWith(res ->
//                    assertAll(
//                            () -> assertNotNull(res),
//                            () -> assertEquals("awesome", res.getContent()),
//                            () -> assertEquals("ps", res.getTitle()),
//                            () -> assertEquals(2L, res.getId())
//                    ))
            .verifyComplete();
  }

  @Test
  void update() {
    StepVerifier
            .create(client.post()
                    .body(Mono.just(MyPost.builder()
                            .title("ps")
                            .content("awesome")
                            .build()), MyPost.class)
                    .retrieve()
                    .bodyToMono(MyPost.class))
            .expectNextCount(1)
            .assertNext(myPost -> assertAll(
                    () -> assertEquals(2L, myPost.getId()),
                    () -> assertEquals("ps", myPost.getTitle()),
                    () -> assertEquals("awesome", myPost.getContent())
            ))
            .verifyComplete();

    testClient.put()
            .uri("/{id}", 2)
            .body(Mono.just(MyPost.builder()
                    .title("pp-title")
                    .content("ps-content")
                    .build()), MyPost.class)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(MyPost.class)
            .value(myPost -> assertAll(
                    () -> assertEquals(2L, myPost.getId()),
                    () -> assertEquals("pp-title", myPost.getTitle()),
                    () -> assertEquals("ps-content", myPost.getContent())
            ))
    ;

  }

  @Test
  void delete() {
    client.delete()
            .uri("/{id}", 2)
            .exchange()
            .subscribe(response -> {
              assertTrue(response.statusCode().is2xxSuccessful());
              assertThrows(Exception.class, () -> client
                      .get().uri("/{id}", 2));
            });

  }
}