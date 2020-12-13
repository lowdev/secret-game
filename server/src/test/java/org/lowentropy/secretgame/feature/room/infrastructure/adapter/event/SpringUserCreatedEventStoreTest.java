package org.lowentropy.secretgame.feature.room.infrastructure.adapter.event;

import org.junit.jupiter.api.Test;
import org.lowentropy.secretgame.feature.room.domain.room.event.UserCreatedEvent;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;

class SpringUserCreatedEventStoreTest {

    @Test
    void test() {
        EmitterProcessor<Long> data = EmitterProcessor.create(1);
        data.subscribe(t -> System.out.println(t));
        FluxSink<Long> sink = data.sink();
        sink.next(10L);
        sink.next(11L);
    }

    @Test
    void test2() {
        Sinks.Many<Long> unicast = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Long> publishedFlux = unicast.asFlux().publish().autoConnect(0);
        publishedFlux.subscribe(t -> System.out.println(t));
        unicast.tryEmitNext(10L);
    }

    @Test
    void test3() {
        SpringEventStore<UserCreatedEvent> eventStore = new SpringEventStore<>();
        eventStore.getFlux().subscribe(e -> System.out.println(e));

        UserCreatedEvent event = new UserCreatedEvent(null, null);
        eventStore.publish(event);
    }
}