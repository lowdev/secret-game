package org.lowentropy.secretgame.feature.infrastructure.web.party;

import org.lowentropy.secretgame.feature.domain.application.PartyUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("parties")
public class PartyController {

    //private PartyUseCase partyUseCase;

    /*public PartyController(PartyUseCase partyUseCase) {
        this.partyUseCase = partyUseCase;
    }*/

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello, World");
    }

    @PostMapping
    public ResponseEntity<String> create(PartyRequest partyRequest) {
        //partyUseCase.create(partyRequest.getGameMasterId());
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }
}
