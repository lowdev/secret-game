package org.lowentropy.secretgame.booter.springboot;

import org.lowentropy.secretgame.feature.domain.port.EventStore;
import org.lowentropy.secretgame.feature.infrastructure.adapter.event.SpringEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @Bean
    EventStore userCreatedEventStore() {
        return new SpringEventStore();
    }
}
