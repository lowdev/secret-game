package org.lowentropy.secretgame.feature.domain.room;

import org.lowentropy.secretgame.feature.domain.party.Party;
import org.lowentropy.secretgame.feature.domain.party.PartyId;
import org.lowentropy.secretgame.feature.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.domain.port.EventStore;
import org.lowentropy.secretgame.feature.domain.user.User;
import org.lowentropy.secretgame.feature.domain.user.UserName;
import org.lowentropy.secretgame.feature.domain.user.event.UserCreatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {

    private EventStore eventStore;

    private RoomWriteRepository repository;

    private RoomId id;

    private List<User> users;

    public Room(RoomWriteRepository repository,  EventStore eventPublisher) {
        this.repository = repository;
        this.eventStore = eventPublisher;
        this.id = new RoomId();
        this.users = new ArrayList<>();
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

    public PartyId addParty() {
        verifyEnoughUserInRoom();

        Party party = new Party(this.users.stream().map(user -> user.getId()).collect(Collectors.toList()));
        return party.getPartyId();
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
}
