package org.lowentropy.secretgame.feature.room.domain.room.event;

import java.util.Map;

public class PartyCreatedEvent extends RoomEvent {
    private String partyId;
    private Map<String, String> userIdToNames;

    public PartyCreatedEvent(String partyId, Map<String, String> userIdToNames) {
        this.partyId = partyId;
        this.userIdToNames = userIdToNames;
    }

    public String getPartyId() {
        return partyId;
    }

    public Map<String, String> getUsers() {
        return this.userIdToNames;
    }
}
