package org.lowentropy.secretgame.feature.party.domain;

import java.util.UUID;

public class PlayerId {
    private UUID uuid;

    public PlayerId(UUID uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return this.uuid.toString();
    }
}
