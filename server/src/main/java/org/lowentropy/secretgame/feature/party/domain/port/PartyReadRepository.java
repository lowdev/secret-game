package org.lowentropy.secretgame.feature.party.domain.port;

import org.lowentropy.secretgame.feature.party.domain.PartyId;
import org.lowentropy.secretgame.feature.party.domain.Player;
import org.lowentropy.secretgame.feature.party.domain.PlayerId;

public interface PartyReadRepository {
    Player get(PartyId partyId, PlayerId playerId);
}
