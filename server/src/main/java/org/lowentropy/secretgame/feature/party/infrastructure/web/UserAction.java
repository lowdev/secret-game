package org.lowentropy.secretgame.feature.party.infrastructure.web;

import org.lowentropy.secretgame.feature.party.domain.*;

import java.util.UUID;

import static org.lowentropy.secretgame.feature.party.domain.ActionType.VOTE;

public class UserAction {

    private String partyId;

    private String playerId;

    private String action;

    private String actionType;

    public PartyId getPartyId() {
        return new PartyId(UUID.fromString(partyId));
    }

    public PlayerId getUserId() {
        return new PlayerId(UUID.fromString(playerId));
    }

    public Action getAction() {
        ActionType actionType = ActionType.valueOf(this.actionType);
        if (ActionType.VOTE == actionType) {
            return new ActionVote(action);
        }

        throw  new IllegalArgumentException("Action Type doesn't exist: " + actionType);
    }
}
