package org.lowentropy.secretgame.feature.domain.port;

import org.lowentropy.secretgame.feature.domain.room.Room;
import org.lowentropy.secretgame.feature.domain.room.RoomId;

import java.util.Optional;

public interface RoomReadRepository {
    Optional<Room> find(RoomId roomId);
}
