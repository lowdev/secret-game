package org.lowentropy.secretgame.feature.party.infrastructure.web;

import org.lowentropy.secretgame.feature.party.domain.application.PartyUseCase;
import org.lowentropy.secretgame.feature.party.domain.application.UserActionCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("parties")
public class PartyController {

    private PartyUseCase partyUseCase;

    public PartyController(PartyUseCase partyUseCase) {
        this.partyUseCase = partyUseCase;
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello, World");
    }

    @PostMapping("{partyId}/users/{userId}/actions")
    public ResponseEntity play(UserAction userActionRequest) {

        UserActionCommand command = new UserActionCommand(
                userActionRequest.getPartyId(),
                userActionRequest.getUserId(),
                userActionRequest.getAction());
        partyUseCase.play(command);

        return ResponseEntity.ok().build();
    }
}
