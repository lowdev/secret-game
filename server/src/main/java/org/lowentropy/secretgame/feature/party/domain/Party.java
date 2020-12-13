package org.lowentropy.secretgame.feature.party.domain;

import java.util.List;

public class Party {
    private PartyId id;
    private Stage stage;
    private List<Player> players;

    public Party(PartyId partyId, List<Player> players) {
        this.id = new PartyId();
        this.stage = Stage.WAITING_PLAYERS;
        this.players = players;
    }

    public String toString() {
        return "id:" + id.toString() + ", stage:" + stage + ", players:" + players;
    }

    public PartyId getPartyId() {
        return getPartyId();
    }
}
