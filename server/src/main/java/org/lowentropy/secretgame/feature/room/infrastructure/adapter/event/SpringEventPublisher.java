package org.lowentropy.secretgame.feature.room.infrastructure.adapter.event;

import org.lowentropy.secretgame.feature.room.domain.common.DomainEvent;
import org.lowentropy.secretgame.feature.room.domain.common.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;

public class SpringEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
