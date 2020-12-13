package org.lowentropy.secretgame.feature.room.infrastructure.web.room;

import org.lowentropy.secretgame.feature.room.domain.user.User;

public class UserResponse {
    private String id;
    private String name;

    public UserResponse(User user) {
        this.id = user.getId().toString();
        this.name = user.getName().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
