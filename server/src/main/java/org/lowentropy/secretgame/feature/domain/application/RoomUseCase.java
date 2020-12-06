package org.lowentropy.secretgame.feature.domain.application;

import org.lowentropy.secretgame.feature.domain.port.RoomReadRepository;
import org.lowentropy.secretgame.feature.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.domain.port.EventStore;
import org.lowentropy.secretgame.feature.domain.room.Room;
import org.lowentropy.secretgame.feature.domain.room.RoomId;
import org.lowentropy.secretgame.feature.domain.user.GameMaster;
import org.lowentropy.secretgame.feature.domain.user.User;
import org.lowentropy.secretgame.feature.domain.user.UserId;
import org.lowentropy.secretgame.feature.domain.user.UserName;
import org.lowentropy.secretgame.feature.domain.user.event.UserCreatedEvent;

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

    public RoomId createRoom(String gameMasterName) {
        GameMaster gameMaster = new GameMaster(writeRepository, eventStore, new UserName(gameMasterName));
        return gameMaster.createRoom();
    }

    public UserId addUser(String roomId, String userName) {
        Room room = getRoom(roomId);
        User user = new User(new UserName(userName));
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
