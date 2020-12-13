package org.lowentropy.secretgame.feature.party.domain.event;

import org.lowentropy.secretgame.feature.party.domain.Action;
import org.lowentropy.secretgame.feature.party.domain.PartyId;
import org.lowentropy.secretgame.feature.party.domain.PlayerId;
import org.lowentropy.secretgame.feature.party.domain.Stage;
import org.lowentropy.secretgame.feature.room.domain.common.DomainEvent;

public class PlayerPlayedEvent implements DomainEvent {
    private PlayerId playerId;
    private PartyId partyId;
    private Stage stage;
    private Action action;

    public PlayerPlayedEvent(PlayerId playerId, PartyId partyId, Action action) {
        this.playerId = playerId;
        this.partyId = partyId;
        this.action = action;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public PartyId getPartyId() {
        return partyId;
    }

    public Action getAction() {
        return action;
    }
}
