package org.lowentropy.secretgame.booter.springboot;

import org.lowentropy.secretgame.feature.domain.application.RoomUseCase;
import org.lowentropy.secretgame.feature.domain.port.RoomReadRepository;
import org.lowentropy.secretgame.feature.domain.port.RoomWriteRepository;
import org.lowentropy.secretgame.feature.domain.port.EventStore;
import org.lowentropy.secretgame.feature.infrastructure.adapter.room.RoomMemoryRepository;
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
    RoomMemoryRepository createRoomRepository() {
        return new RoomMemoryRepository();
    }
}
