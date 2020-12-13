package org.lowentropy.secretgame.feature.room.domain.port;

import org.lowentropy.secretgame.feature.room.domain.user.GameMaster;
import org.lowentropy.secretgame.feature.room.domain.user.UserId;

public interface GameMasterReadRepository {
    GameMaster find(UserId gameMasterId);
}
