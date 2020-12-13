package org.lowentropy.secretgame.feature.room.domain.room;

import java.util.Objects;
import java.util.UUID;

public class RoomId {
    private UUID uuid;

    public RoomId() {
        this.uuid = UUID.randomUUID();
    }

    public RoomId(UUID uuid) {
        this.uuid = uuid;
    }

    public static RoomId of(String roomId) {
        return new RoomId(UUID.fromString(roomId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoomId roomId = (RoomId) o;
        return Objects.equals(uuid, roomId.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
