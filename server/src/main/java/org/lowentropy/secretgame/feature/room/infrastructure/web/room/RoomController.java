package org.lowentropy.secretgame.feature.room.infrastructure.web.room;

import org.lowentropy.secretgame.feature.room.domain.application.RoomUseCase;
import org.lowentropy.secretgame.feature.room.domain.room.Room;
import org.lowentropy.secretgame.feature.room.domain.room.RoomId;
import org.lowentropy.secretgame.feature.room.domain.user.User;
import org.lowentropy.secretgame.feature.room.domain.user.UserId;
import org.lowentropy.secretgame.feature.room.domain.room.event.UserCreatedEvent;
import org.lowentropy.secretgame.feature.room.domain.user.UserName;
import org.lowentropy.secretgame.feature.room.infrastructure.adapter.event.SpringEventStore;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/rooms")
public class RoomController {

    private  Flux<UserCreatedEvent> events;

    private RoomUseCase userCase;

    public RoomController(RoomUseCase userCase, SpringEventStore<UserCreatedEvent> eventStore) {
        this.userCase = userCase;
        this.events = eventStore.getFlux().share();
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable String id) {
        Room room = this.userCase.getRoom(id);

        return ResponseEntity.ok(new RoomResponse(room));
    }

    @GetMapping
    public ResponseEntity search(@RequestParam String roomMasterName) {
        Optional<Room> room = this.userCase.search(new UserName(roomMasterName));

        return room.isPresent() ? ResponseEntity.ok(new RoomResponse(room.get())) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody UserRequest gameMasterName) {
        Room room = this.userCase.createRoom(gameMasterName.getUsername());
        return ResponseEntity.ok(new RoomResponse(room));
    }

    @PostMapping("{id}/users")
    public ResponseEntity<String> createUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
        UserId userId = this.userCase.addUser(id, new UserName(userRequest.getUsername()));
        return ResponseEntity.ok(userId.toString());
    }

    @GetMapping("{id}/users/{userId}")
    public @ResponseBody ResponseEntity<UserResponse> getUser(@PathVariable String id, @PathVariable String userId) {
        User user = this.userCase.getUser(id, new UserId(UUID.fromString(userId)));
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping(path = "{id}/events/user", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> eventsStream(@PathVariable String id) {
        this.userCase.ping(id);
        return this.events
                .filter(event -> event.getRoomId().equals(RoomId.of(id)))
                .map(event -> ServerSentEvent.builder(event.getUserName().toString()).build());
    }
}
