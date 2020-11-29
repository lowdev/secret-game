package org.lowentropy.secretgame.feature.infrastructure.adapter.room;

import org.lowentropy.secretgame.feature.domain.port.RoomReadRepository;
import org.lowentropy.secretgame.feature.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.domain.room.Room;
import org.lowentropy.secretgame.feature.domain.room.RoomId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomMemoryRepository implements RoomWriteRepository, RoomReadRepository {
    private List<Room> rooms = new ArrayList<>();

    @Override
    public void save(Room room) {
        rooms.add(room);
    }

    @Override
    public Optional<Room> find(RoomId roomId) {
        return rooms.stream().filter(r -> r.hasSameId(roomId)).findFirst();
    }
}
