package org.lowentropy.secretgame.feature.infrastructure.web.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lowentropy.secretgame.feature.domain.application.RoomUseCase;
import org.lowentropy.secretgame.feature.domain.room.Room;
import org.lowentropy.secretgame.feature.domain.room.RoomId;
import org.lowentropy.secretgame.feature.domain.user.UserId;
import org.lowentropy.secretgame.feature.domain.user.event.UserCreatedEvent;
import org.lowentropy.secretgame.feature.infrastructure.adapter.event.SpringEventStore;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("rooms")
public class RoomController {

    private  Flux<UserCreatedEvent> events;

    private SpringEventStore<UserCreatedEvent> eventStore;

    private RoomUseCase userCase;

    public RoomController(RoomUseCase userCase,  SpringEventStore<UserCreatedEvent> eventStore) {
        this.userCase = userCase;
        this.eventStore = eventStore;
        this.events = eventStore.getFlux().share();
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable String id) {
        Room room = this.userCase.getRoom(id);

        return ResponseEntity.ok(new RoomResponse(room));
    }

    @PostMapping
    public ResponseEntity<String> create(String gameMasterName) {
        RoomId roomId = this.userCase.createRoom(gameMasterName);
        return ResponseEntity.ok(roomId.toString());
    }

    @PostMapping("{id}/users")
    public ResponseEntity<String> createUser(@PathVariable String id, @RequestBody String userName) {
        UserId userId = this.userCase.addUser(id, userName);
        return ResponseEntity.ok(userId.toString());
    }

    @GetMapping(path = "{id}/events/user", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> eventsStream(@PathVariable String id) {
        return this.events
                .filter(event -> event.getRoomId().equals(RoomId.of(id)))
                .map(event -> ServerSentEvent.builder(event.getUserName().toString()).build());
    }
}
