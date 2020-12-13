package org.lowentropy.secretgame.feature.room.domain.party;

import org.lowentropy.secretgame.feature.room.domain.user.UserId;
import java.util.List;

public class Party {
    private PartyId id;
    private Stage stage;
    private List<UserId> players;

    public Party(List<UserId> players) {
        this.id = new PartyId();
        this.stage = Stage.WAITING_PLAYERS;
        this.players = players;
    }

    public String toString() {
        return "id:" + id.toString() + ", stage:" + stage + ", players:" + players;
    }

    public PartyId getPartyId() {
        return id;
    }
}
