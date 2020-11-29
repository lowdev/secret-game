package org.lowentropy.secretgame.feature.infrastructure.web.room;

import java.io.Serializable;

public class UserCreatedEventResponse implements Serializable {
    private String roomId;
    private String userId;

    public UserCreatedEventResponse(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getUserId() {
        return userId;
    }
}
