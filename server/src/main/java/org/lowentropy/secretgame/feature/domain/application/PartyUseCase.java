package org.lowentropy.secretgame.feature.domain.application;

import org.lowentropy.secretgame.feature.domain.port.GameMasterReadRepository;
import org.lowentropy.secretgame.feature.domain.port.RoomReadRepository;
import org.lowentropy.secretgame.feature.domain.user.GameMaster;
import org.lowentropy.secretgame.feature.domain.user.UserId;

public class PartyUseCase {

    private GameMasterReadRepository gameMasterReadRepository;
    private RoomReadRepository roomReadRepository;

    public PartyUseCase(GameMasterReadRepository gameMasterReadRepository, RoomReadRepository roomReadRepository) {
        this.gameMasterReadRepository = gameMasterReadRepository;
        this.roomReadRepository = roomReadRepository;
    }

    public void create(UserId gameMasterId) {
        GameMaster gameMaster = gameMasterReadRepository.find(gameMasterId);
        gameMaster.startParty();
    }
}
