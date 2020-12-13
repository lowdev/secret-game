package org.lowentropy.secretgame.feature.room.domain.port;

import org.lowentropy.secretgame.feature.room.domain.room.Room;

public interface RoomWriteRepository {
    void save(Room room);
}
