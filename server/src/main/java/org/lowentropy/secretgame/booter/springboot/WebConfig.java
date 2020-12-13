package org.lowentropy.secretgame.booter.springboot;

import org.lowentropy.secretgame.feature.party.infrastructure.web.PartyController;
import org.lowentropy.secretgame.feature.room.infrastructure.web.room.RoomController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan( basePackageClasses = { PartyController.class, RoomController.class })
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}