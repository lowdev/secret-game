package org.lowentropy.secretgame.feature.party.domain;

public class ActionVote implements Action {

    public String action;

    public ActionVote(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public ActionType getType() {
        return ActionType.VOTE;
    }
}
