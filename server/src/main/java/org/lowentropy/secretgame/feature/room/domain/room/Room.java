package org.lowentropy.secretgame.feature.room.domain.room;

import org.lowentropy.secretgame.feature.room.domain.party.Party;
import org.lowentropy.secretgame.feature.room.domain.room.event.PartyCreatedEvent;
import org.lowentropy.secretgame.feature.room.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.room.domain.port.EventStore;
import org.lowentropy.secretgame.feature.room.domain.user.GameMaster;
import org.lowentropy.secretgame.feature.room.domain.user.User;
import org.lowentropy.secretgame.feature.room.domain.user.UserId;
import org.lowentropy.secretgame.feature.room.domain.user.UserName;
import org.lowentropy.secretgame.feature.room.domain.room.event.UserCreatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {

    private EventStore eventStore;

    private RoomWriteRepository repository;

    private RoomId id;

    private GameMaster gameMaster;

    private List<User> users;

    private List<Party> parties;

    public Room(RoomWriteRepository repository, EventStore eventPublisher, GameMaster gameMaster) {
        this.repository = repository;
        this.eventStore = eventPublisher;
        this.id = new RoomId();
        this.users = new ArrayList<>();
        this.gameMaster = gameMaster;
    }

    public RoomId getId() {
        return id;
    }

    public void addUser(User user) {
        checkNameExist(user.getName());
        this.users.add(user);
        this.repository.save(this);

        UserCreatedEvent event = new UserCreatedEvent(this.id, user.getName());
        this.eventStore.publish(event);
    }

    public void checkNameExist(UserName userName) {
        boolean hasAlreadyAnUserWithThisName = this.users.stream().anyMatch(u -> userName.equals(u.getName()));
        if (hasAlreadyAnUserWithThisName) {
            throw new IllegalArgumentException("An user has already taken this name in this room. Please choose another name.");
        }
    }

    public void addParty() {
        verifyEnoughUserInRoom();

        Party party = new Party(this.users.stream().map(user -> user.getId()).collect(Collectors.toList()));
        parties.add(party);

        Map<String, String> idToNames = this.users.stream()
                .collect(Collectors.toMap(u -> u.getId().toString(), u -> u.getName().toString()));
        eventStore.publish(new PartyCreatedEvent(party.getPartyId().toString(), idToNames));
    }

    private void verifyEnoughUserInRoom() {
        if (users.size() < 4) {
            throw new IllegalArgumentException("Need 3 users in room");
        }
    }

    public boolean hasSameId(RoomId roomId) {
        return this.id.equals(roomId);
    }

    public Map<String, String> toJson() {
        return Map.ofEntries(
                Map.entry("id", id.toString()),
                Map.entry("users", users.toString())
        );
    }

    public List<String> getUserNames() {
        return this.users.stream()
                .map(u -> u.getName().toString())
                .collect(Collectors.toList());
    }

    public boolean isOwner(UserName userName) {
        return gameMaster.isMyName(userName);
    }

    public boolean hasUser(UserName userName) {
        if (this.gameMaster.isMyName(userName)) {
            return true;
        }

       return this.users.stream()
                .anyMatch(u -> u.getName().equals(userName));
    }

    public UserId getUser(UserName userName) {
        if (this.gameMaster.isMyName(userName)) {
            return this.gameMaster.getUserId();
        }

        return this.users.stream()
                .filter(u -> u.getName().equals(userName))
                .findFirst()
                .get().getId();
    }

    public User getUser(UserId userId) {
        if (this.gameMaster.hasSameId(userId)) {
            return this.gameMaster;
        }

        return this.users.stream()
                .filter(u -> u.hasSameId(userId))
                .findFirst()
                .get();
    }

    public UserName getMasterName() {
        return this.gameMaster.getName();
    }

    public GameMaster getGameMaster() {
        return this.gameMaster;
    }
}
