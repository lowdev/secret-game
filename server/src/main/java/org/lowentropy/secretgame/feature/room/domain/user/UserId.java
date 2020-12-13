package org.lowentropy.secretgame.feature.room.domain.user;

import java.util.Objects;
import java.util.UUID;

public class UserId {
    private UUID uuid;

    public UserId() {
        this.uuid = UUID.randomUUID();
    }

    public UserId(UUID uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return this.uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(uuid, userId.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
