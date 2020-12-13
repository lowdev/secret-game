package org.lowentropy.secretgame.feature.room.domain.port;

import org.lowentropy.secretgame.feature.room.domain.common.DomainEvent;

public interface EventStore<T extends DomainEvent> {
    void publish(T domainEvent);
}
