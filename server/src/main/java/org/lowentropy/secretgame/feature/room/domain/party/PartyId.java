package org.lowentropy.secretgame.feature.room.domain.party;

import java.util.UUID;

public class PartyId {

    private UUID uuid;

    public PartyId() {
        this.uuid = UUID.randomUUID();
    }

    public String toString() {
        return uuid.toString();
    }
}
