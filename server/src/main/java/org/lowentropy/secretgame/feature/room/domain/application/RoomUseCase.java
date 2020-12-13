package org.lowentropy.secretgame.feature.room.domain.application;

import org.lowentropy.secretgame.feature.room.domain.port.RoomReadRepository;
import org.lowentropy.secretgame.feature.room.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.room.domain.port.EventStore;
import org.lowentropy.secretgame.feature.room.domain.room.Room;
import org.lowentropy.secretgame.feature.room.domain.room.RoomId;
import org.lowentropy.secretgame.feature.room.domain.user.GameMaster;
import org.lowentropy.secretgame.feature.room.domain.user.User;
import org.lowentropy.secretgame.feature.room.domain.user.UserId;
import org.lowentropy.secretgame.feature.room.domain.user.UserName;
import org.lowentropy.secretgame.feature.room.domain.room.event.UserCreatedEvent;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class RoomUseCase {

    private RoomWriteRepository writeRepository;
    private RoomReadRepository readRepository;
    private EventStore eventStore;

    public RoomUseCase(RoomWriteRepository writeRepository, RoomReadRepository readRepository, EventStore eventStore) {
        this.writeRepository = writeRepository;
        this.eventStore = eventStore;
        this.readRepository = readRepository;
    }

    public Room createRoom(String gameMasterName) {
        GameMaster gameMaster = new GameMaster(writeRepository, eventStore, new UserName(gameMasterName));
        return gameMaster.createRoom();
    }

    public UserId addUser(String roomId, UserName userName) {
        Room room = getRoom(roomId);
        if (room.hasUser(userName)) {
            return room.getUser(userName);
        }

        User user = new User(userName);
        user.join(room);

        return user.getId();
    }

    public Room getRoom(String roomId) {
        return readRepository.find(new RoomId(UUID.fromString(roomId)))
                .orElseThrow(() -> new IllegalArgumentException("No room with this id: " + roomId));
    }

    public void ping(String roomId) {
        UserCreatedEvent event = new UserCreatedEvent(new RoomId(UUID.fromString(roomId)), UserName.NULL);
        new Timer().schedule(new PingTask(eventStore, event), 0, 40000);
    }

    public Optional<Room> search(UserName roomMasterName) {
        return readRepository.findByRoomMasterName(roomMasterName);
    }

    public User getUser(String id, UserId userId) {
        Room room = getRoom(id);
        return room.getUser(userId);
    }

    private class PingTask extends TimerTask {
        private final EventStore eventStore;
        private final UserCreatedEvent userEvent;

        public PingTask(EventStore eventStore, UserCreatedEvent userEvent) {
            this.userEvent = userEvent;
            this.eventStore = eventStore;
        }

        @Override
        public void run() {
            eventStore.publish(userEvent);
        }
    }
}
