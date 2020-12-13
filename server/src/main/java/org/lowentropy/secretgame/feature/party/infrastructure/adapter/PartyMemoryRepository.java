package org.lowentropy.secretgame.feature.party.infrastructure.adapter;

import org.lowentropy.secretgame.feature.party.domain.PartyId;
import org.lowentropy.secretgame.feature.party.domain.Player;
import org.lowentropy.secretgame.feature.party.domain.PlayerId;
import org.lowentropy.secretgame.feature.party.domain.port.PartyReadRepository;

public class PartyMemoryRepository implements PartyReadRepository {
    @Override
    public Player get(PartyId partyId, PlayerId playerId) {
        return null;
    }
}
