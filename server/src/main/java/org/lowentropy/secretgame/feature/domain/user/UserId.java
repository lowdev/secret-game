package org.lowentropy.secretgame.feature.domain.user;

import java.util.UUID;

public class UserId {
    private UUID uuid;

    public UserId() {
        this.uuid = UUID.randomUUID();
    }

    public String toString() {
        return this.uuid.toString();
    }
}
