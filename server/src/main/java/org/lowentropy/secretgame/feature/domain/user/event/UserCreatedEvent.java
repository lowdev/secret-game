package org.lowentropy.secretgame.feature.domain.user.event;

import org.lowentropy.secretgame.feature.domain.common.DomainEvent;
import org.lowentropy.secretgame.feature.domain.room.RoomId;
import org.lowentropy.secretgame.feature.domain.user.UserId;
import org.lowentropy.secretgame.feature.domain.user.UserName;

public class UserCreatedEvent implements DomainEvent {
    private RoomId roomId;
    private UserName userName;

    public UserCreatedEvent( RoomId roomId, UserName userName) {
        this.roomId = roomId;
        this.userName = userName;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public UserName getUserName() {
        return userName;
    }
}
