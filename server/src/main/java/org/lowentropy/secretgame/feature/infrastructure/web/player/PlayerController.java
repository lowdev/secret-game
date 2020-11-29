package org.lowentropy.secretgame.feature.infrastructure.web.player;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("players")
public class PlayerController {

    @PostMapping("{id}/events")
    public ResponseEntity<String> getEvents() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }
}
