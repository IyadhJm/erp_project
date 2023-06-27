package com.example.gestionbdg.queries.services;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;


public interface EventSourcingService {
    DomainEventStream eventsByGbdgId(String bdgId);
}
