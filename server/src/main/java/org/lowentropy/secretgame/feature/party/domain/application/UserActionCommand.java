package org.lowentropy.secretgame.feature.party.domain.application;

import org.lowentropy.secretgame.feature.party.domain.Action;
import org.lowentropy.secretgame.feature.party.domain.PartyId;
import org.lowentropy.secretgame.feature.party.domain.PlayerId;

public class UserActionCommand {
    private PartyId partyId;

    private PlayerId playerId;

    private Action action;

    public UserActionCommand(PartyId partyId, PlayerId playerId, Action action) {
        this.partyId = partyId;
        this.playerId = playerId;
        this.action = action;
    }

    public PartyId getPartyId() {
        return partyId;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public Action getAction() {
        return action;
    }
}
