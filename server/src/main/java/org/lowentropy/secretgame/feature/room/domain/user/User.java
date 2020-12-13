package org.lowentropy.secretgame.feature.room.domain.user;

import org.lowentropy.secretgame.feature.room.domain.room.Room;

public class User {
    protected UserId id;
    protected UserName name;

    public User(UserName name) {
        this.id = new UserId();
        this.name = name;
    }

    public void join(Room room) {
        room.addUser(this);
    }

    public UserId getId() {
        return id;
    }

    public UserName getName() {
        return name;
    }

    public boolean hasSameId(UserId userId) {
        return id.equals(userId);
    }
}
