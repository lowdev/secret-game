package org.lowentropy.secretgame.feature.room.domain.common;

public interface EventPublisher {
    void publish(DomainEvent domainEvent);
}
