package org.lowentropy.secretgame.feature.domain.port;

import org.lowentropy.secretgame.feature.domain.user.GameMaster;
import org.lowentropy.secretgame.feature.domain.user.UserId;

public interface GameMasterReadRepository {
    GameMaster find(UserId gameMasterId);
}
