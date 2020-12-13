package org.lowentropy.secretgame.feature.room.domain.room.event;

import org.lowentropy.secretgame.feature.room.domain.common.DomainEvent;

import java.util.Date;
import java.util.UUID;

public class RoomEvent implements DomainEvent {
    private UUID id;
    private Date date;

    public RoomEvent() {
        this.id = UUID.randomUUID();
        this.date = new Date();
    }
}
