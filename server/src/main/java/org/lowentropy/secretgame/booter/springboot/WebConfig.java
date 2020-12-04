package org.lowentropy.secretgame.booter.springboot;

import org.lowentropy.secretgame.feature.infrastructure.web.party.PartyController;
import org.lowentropy.secretgame.feature.infrastructure.web.room.RoomController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackageClasses = { PartyController.class, RoomController.class })
public class WebConfig {
}