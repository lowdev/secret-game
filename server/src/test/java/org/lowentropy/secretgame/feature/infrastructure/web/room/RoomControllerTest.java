package org.lowentropy.secretgame.feature.infrastructure.web.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.lowentropy.secretgame.feature.domain.room.RoomId;
import org.lowentropy.secretgame.feature.domain.user.UserId;
import org.lowentropy.secretgame.feature.domain.user.event.UserCreatedEvent;

class RoomControllerTest {

    @Test
    void testMapper() {
        UserCreatedEvent event = new UserCreatedEvent(new RoomId(), new UserId());
    }

    private static String convertMapToJson(UserCreatedEvent evetp) {
        try {
            return new ObjectMapper().writeValueAsString(evetp);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Impossible to convert", e);
        }
    }
}