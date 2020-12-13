package org.lowentropy.secretgame.booter.springboot;

import org.lowentropy.secretgame.feature.party.domain.application.PartyUseCase;
import org.lowentropy.secretgame.feature.party.domain.port.PartyReadRepository;
import org.lowentropy.secretgame.feature.party.infrastructure.adapter.PartyMemoryRepository;
import org.lowentropy.secretgame.feature.room.domain.application.RoomUseCase;
import org.lowentropy.secretgame.feature.room.domain.port.RoomReadRepository;
import org.lowentropy.secretgame.feature.room.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.room.domain.port.EventStore;
import org.lowentropy.secretgame.feature.room.domain.room.Room;
import org.lowentropy.secretgame.feature.room.infrastructure.adapter.room.RoomMemoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore({EventConfig.class})
public class DomainConfig {

    @Bean
    RoomUseCase createRoomUseCase(@Qualifier("RoomReadRepository") RoomReadRepository roomReadRepository,
                                  @Qualifier("RoomWriteRepository") RoomWriteRepository roomWriteRepository,
                                  EventStore eventStore) {
        return new RoomUseCase(roomWriteRepository, roomReadRepository, eventStore);
    }

    @Bean(name = "RoomReadRepository")
    RoomReadRepository createRoomReadRepository(RoomMemoryRepository roomMemoryRepository) {
        return roomMemoryRepository;
    }

    @Bean(name = "RoomWriteRepository")
    RoomWriteRepository createRoomWriteRepository(RoomMemoryRepository roomMemoryRepository) {
        return roomMemoryRepository;
    }

    @Bean
    RoomMemoryRepository createRoomRepository(EventStore eventStore) {
        return new RoomMemoryRepository(eventStore);
    }

    @Bean
    PartyReadRepository createPartyReadRepository() {
        return new PartyMemoryRepository();
    }

    @Bean
    PartyUseCase createPartyUseCase(PartyReadRepository partyReadRepository) {
        return new PartyUseCase(partyReadRepository);
    }
}
