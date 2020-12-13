package org.lowentropy.secretgame.feature.room.infrastructure.adapter.room;

import org.lowentropy.secretgame.feature.room.domain.port.EventStore;
import org.lowentropy.secretgame.feature.room.domain.port.RoomReadRepository;
import org.lowentropy.secretgame.feature.room.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.room.domain.room.Room;
import org.lowentropy.secretgame.feature.room.domain.room.RoomId;
import org.lowentropy.secretgame.feature.room.domain.user.GameMaster;
import org.lowentropy.secretgame.feature.room.domain.user.User;
import org.lowentropy.secretgame.feature.room.domain.user.UserName;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomMemoryRepository implements RoomWriteRepository, RoomReadRepository {

    private EventStore eventPublisher;

    private List<Room> rooms = new ArrayList<>();

    public RoomMemoryRepository(EventStore eventPublisher) {
        this.eventPublisher = eventPublisher;
        rooms.add(createRoomStep1());
    }

    private Room createRoomStep1() {
        GameMaster gameMaster = new GameMaster(this, eventPublisher, new UserName("David"));
        Room room = new Room(this, eventPublisher, gameMaster);
        room.addUser(new User(new UserName("Alice")));
        room.addUser(new User(new UserName("Pierre")));
        room.addUser(new User(new UserName("Julie")));

        return room;
    }

    @Override
    public void save(Room room) {
        rooms.add(room);
    }

    @Override
    public Optional<Room> find(RoomId roomId) {
        return rooms.stream().filter(r -> r.hasSameId(roomId)).findFirst();
    }

    @Override
    public Optional<Room> findByRoomMasterName(UserName userName) {
        return rooms.stream().filter(r -> r.isOwner(userName)).findFirst();
    }
}
