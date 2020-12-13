package org.lowentropy.secretgame.feature.room.domain.port;

import org.lowentropy.secretgame.feature.room.domain.room.Room;
import org.lowentropy.secretgame.feature.room.domain.room.RoomId;
import org.lowentropy.secretgame.feature.room.domain.user.UserName;

import java.util.Optional;

public interface RoomReadRepository {
    Optional<Room> find(RoomId roomId);

    Optional<Room> findByRoomMasterName(UserName roomId);
}
