package org.lowentropy.secretgame.feature.domain.common;

public interface EventPublisher {
    void publish(DomainEvent domainEvent);
}
