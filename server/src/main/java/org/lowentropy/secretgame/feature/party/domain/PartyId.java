package org.lowentropy.secretgame.feature.party.domain;

import java.util.UUID;

public class PartyId {

    private UUID uuid;

    public PartyId(UUID uuid) {
        this.uuid = uuid;
    }

    public PartyId() {
        uuid = UUID.randomUUID();
    }

    public String toString() {
        return uuid.toString();
    }
}
