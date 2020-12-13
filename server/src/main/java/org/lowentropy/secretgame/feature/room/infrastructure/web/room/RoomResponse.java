package org.lowentropy.secretgame.feature.room.infrastructure.web.room;

import org.lowentropy.secretgame.feature.room.domain.room.Room;

import java.util.List;

public class RoomResponse {

    private String id;

    private String gameMasterName;

    private String gameMasterId;

    private List<String> users;

    public RoomResponse(Room room) {
        this.id = room.getId().toString();
        this.users = room.getUserNames();
        this.gameMasterName = room.getMasterName().toString();
        this.gameMasterId = room.getGameMaster().getUserId().toString();
    }

    public String getId() {
        return id;
    }

    public String getGameMasterName() {
        return gameMasterName;
    }

    public String getGameMasterId() {
        return gameMasterId;
    }

    public List<String> getUsers() {
        return users;
    }
}
