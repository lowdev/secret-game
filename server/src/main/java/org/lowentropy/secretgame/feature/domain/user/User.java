package org.lowentropy.secretgame.feature.domain.user;

import org.lowentropy.secretgame.feature.domain.room.Room;

public class User {
    private UserId id;
    private UserName name;

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
}
