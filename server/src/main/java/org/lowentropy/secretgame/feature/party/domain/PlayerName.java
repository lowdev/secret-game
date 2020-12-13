package org.lowentropy.secretgame.feature.party.domain;

import java.util.Objects;

public class PlayerName {
    public static final PlayerName NULL = new PlayerName(null);

    private String name;

    public PlayerName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName userName = (PlayerName) o;
        return Objects.equals(name, userName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
