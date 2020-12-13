package org.lowentropy.secretgame.feature.room.domain.room.event;

import org.lowentropy.secretgame.feature.room.domain.room.RoomId;
import org.lowentropy.secretgame.feature.room.domain.user.UserName;

public class UserCreatedEvent extends RoomEvent {
    private RoomId roomId;
    private UserName userName;

    public UserCreatedEvent(RoomId roomId, UserName userName) {
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
