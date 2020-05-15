package org.psawesome.rsocketcrudclient.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class RSocketRequesterBean {
    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .connectTcp("localhost", 7000)
                .block();
    }
}
