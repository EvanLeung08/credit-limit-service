package com.eshare.message;

import com.alibaba.cola.event.DomainEventI;
import com.alibaba.cola.event.EventBusI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DomainEventPublisher, this is for demo purpose, the Event is sent to EventBus
 * <p>
 * Normally DomainEvent should be sent to Messaging Middleware
 *
 * @author Evan Leung
 * @date 2019-01-04 11:05 AM
 */
@Component
public class DomainEventPublisher {

    @Autowired
    private EventBusI eventBus;

    public void publish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
    }
}
