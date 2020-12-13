package org.lowentropy.secretgame.feature.room.domain.user;

import org.lowentropy.secretgame.feature.room.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.room.domain.port.EventStore;
import org.lowentropy.secretgame.feature.room.domain.room.Room;
import org.lowentropy.secretgame.feature.room.domain.room.RoomId;

public class GameMaster extends User {
    private RoomWriteRepository repository;
    private EventStore eventStore;

    private Room room;

    public GameMaster(RoomWriteRepository repository, EventStore eventStore, UserName name) {
        super(name);
        this.repository = repository;
        this.eventStore = eventStore;
    }

    public Room createRoom() {
        this.room = new Room(repository, eventStore, this);
        repository.save(this.room);
        return this.room;
    }

    public void startParty() {
        this.room.addParty();
    }

    public boolean isMyName(UserName userName) {
        return this.name.equals(userName);
    }

    public UserName getName() {
        return this.name;
    }

    public UserId getUserId() {
        return this.id;
    }

    public boolean hasSameId(UserId userId) {
        return id.equals(userId);
    }
}
