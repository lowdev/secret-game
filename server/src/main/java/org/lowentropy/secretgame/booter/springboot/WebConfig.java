package org.lowentropy.secretgame.booter.springboot;

import org.lowentropy.secretgame.feature.infrastructure.web.party.PartyController;
import org.lowentropy.secretgame.feature.infrastructure.web.room.RoomController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.Executors;

@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan( basePackageClasses = { PartyController.class, RoomController.class})
public class WebConfig implements WebMvcConfigurer, AsyncConfigurer {

    @Bean
    protected WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
                configurer.setTaskExecutor(getTaskExecutor());
            }
        };
    }

    @Bean
    protected ConcurrentTaskExecutor getTaskExecutor() {
        return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(5));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}