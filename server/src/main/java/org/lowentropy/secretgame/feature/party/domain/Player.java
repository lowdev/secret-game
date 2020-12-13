package org.lowentropy.secretgame.feature.party.domain;

import org.lowentropy.secretgame.feature.party.domain.event.PlayerPlayedEvent;
import org.lowentropy.secretgame.feature.room.domain.port.EventStore;

public class Player {
    private PlayerId id;
    private PlayerName name;
    private Party party;
    private EventStore eventStore;

    public Player(PlayerId id, PlayerName name, Party party, EventStore eventStore) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "id:" + id + ", name:" + name;
    }

    public void play(Action action) {
        // state party should be good to play
        eventStore.publish(new PlayerPlayedEvent(id, party.getPartyId(), action));
    }
}
