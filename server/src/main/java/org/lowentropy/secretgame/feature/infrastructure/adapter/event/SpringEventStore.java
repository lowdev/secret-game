package org.lowentropy.secretgame.feature.infrastructure.adapter.event;

import org.lowentropy.secretgame.feature.domain.common.DomainEvent;
import org.lowentropy.secretgame.feature.domain.port.EventStore;
import org.lowentropy.secretgame.feature.domain.user.event.UserCreatedEvent;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SpringEventStore<T extends DomainEvent> implements EventStore<T>, Publisher<T> {
    private final Flux<T> publishedFlux;

    private final Sinks.Many<T> unicast;

    public SpringEventStore() {
        this.unicast = Sinks.many().unicast().onBackpressureBuffer();
        this.publishedFlux = this.unicast.asFlux().publish().autoConnect(0);
    }

    public void publish(T event) {
        this.unicast.tryEmitNext(event);
    }

    public Flux<T> getFlux() {
        return this.publishedFlux;
    }

    @Override
    public void subscribe(Subscriber<? super T> s) {
        this.publishedFlux.subscribe(e -> System.out.println(e));
    }
}
