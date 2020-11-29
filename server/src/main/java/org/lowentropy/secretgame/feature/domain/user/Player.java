package org.lowentropy.secretgame.feature.domain.user;

import org.lowentropy.secretgame.feature.domain.room.Room;

public class Player {
    private UserId id;
    private UserName name;

    public Player(UserName name) {
        this.id = new UserId();
        this.name = name;
    }

    public String toString() {
        return "id:" + id + ", name:" + name;
    }

    public void join(Room davidRoom) {
    }
}
