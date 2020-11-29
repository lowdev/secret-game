package org.lowentropy.secretgame.feature.domain.port;

import org.lowentropy.secretgame.feature.domain.room.Room;

public interface RoomWriteRepository {
    void save(Room room);
}
