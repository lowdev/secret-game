package org.lowentropy.secretgame.feature.party.domain.application;

import org.lowentropy.secretgame.feature.party.domain.Party;
import org.lowentropy.secretgame.feature.party.domain.PartyId;
import org.lowentropy.secretgame.feature.party.domain.event.PlayerPlayedEvent;

import java.util.UUID;

public class PlayerPlayedEventHandler {
    public void handle(PlayerPlayedEvent event) {
        // faire evoluer le state ?
    }
}
