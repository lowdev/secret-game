package org.lowentropy.secretgame.feature.infrastructure.web.room;

import org.lowentropy.secretgame.feature.domain.room.Room;

import java.util.List;

public class RoomResponse {

    private String id;

    private List<String> users;

    public RoomResponse(Room room) {
        this.id = room.getId().toString();
        this.users = room.getUserNames();
    }

    public String getId() {
        return id;
    }

    public List<String> getUsers() {
        return users;
    }
}
