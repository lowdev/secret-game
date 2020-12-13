package org.lowentropy.secretgame.feature.party.domain.application;

import org.lowentropy.secretgame.feature.party.domain.*;
import org.lowentropy.secretgame.feature.party.domain.port.PartyWriteRepository;
import org.lowentropy.secretgame.feature.room.domain.room.event.PartyCreatedEvent;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class PartyCreatedEventHandler {
    private PartyWriteRepository repository;

    public void handle(PartyCreatedEvent event) {
        // Should attribute player role here.
        // President and ministre
        Party party = new Party(new PartyId(UUID.fromString(event.getPartyId())), map(event.getUsers()));
        this.repository.save(party);
    }

    private List<Player> map(Map<String, String> idToNames) {
        return idToNames.entrySet().stream()
                .map(entry -> new Player(new PlayerId(UUID.fromString(entry.getKey())), new PlayerName(entry.getValue()), null, null))
                .collect(Collectors.toList());
    }
}
