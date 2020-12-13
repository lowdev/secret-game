package org.lowentropy.secretgame.feature.party.domain.application;

import org.lowentropy.secretgame.feature.party.domain.Player;
import org.lowentropy.secretgame.feature.party.domain.port.PartyReadRepository;

public class PartyUseCase {

    private PartyReadRepository repository;

    public PartyUseCase(PartyReadRepository repository) {
        this.repository = repository;
    }

    public void play(UserActionCommand command) {
        Player player = repository.get(command.getPartyId(), command.getPlayerId());
        player.play(command.getAction());
    }
}
