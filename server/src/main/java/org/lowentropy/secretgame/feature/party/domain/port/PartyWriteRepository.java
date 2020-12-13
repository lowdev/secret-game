package org.lowentropy.secretgame.feature.party.domain.port;

import org.lowentropy.secretgame.feature.party.domain.Party;

public interface PartyWriteRepository {
    void save(Party party);
}
