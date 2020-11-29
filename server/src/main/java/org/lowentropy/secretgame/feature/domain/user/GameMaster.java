package org.lowentropy.secretgame.feature.domain.user;

import org.lowentropy.secretgame.feature.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.domain.port.EventStore;
import org.lowentropy.secretgame.feature.domain.room.Room;
import org.lowentropy.secretgame.feature.domain.room.RoomId;

public class GameMaster {
    private RoomWriteRepository repository;
    private EventStore eventStore;

    private UserId id;
    private UserName name;
    private Room room;

    public GameMaster(RoomWriteRepository repository, EventStore eventStore, UserName name) {
        this.repository = repository;
        this.eventStore = eventStore;
        this.id = new UserId();
        this.name = name;
    }

    public RoomId createRoom() {
        this.room = new Room(repository, eventStore);
        repository.save(this.room);
        return this.room.getId();
    }

    public void startParty() {
        this.room.addParty();
    }
}
