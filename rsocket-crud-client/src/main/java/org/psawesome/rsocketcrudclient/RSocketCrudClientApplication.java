package org.psawesome.rsocketcrudclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class RSocketCrudClientApplication {

    public static void main(String[] args) {
//        System.setProperty("spring.profiles.active", "dev");
        SpringApplication.run(RSocketCrudClientApplication.class, args);
    }

}
