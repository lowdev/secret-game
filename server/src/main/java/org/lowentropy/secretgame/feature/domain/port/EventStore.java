package org.lowentropy.secretgame.feature.domain.port;

import org.lowentropy.secretgame.feature.domain.common.DomainEvent;

public interface EventStore<T extends DomainEvent> {
    void publish(T domainEvent);
}
