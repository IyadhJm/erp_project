package org.oga.gestioncras.queries.services;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;


public interface EventSourcingService {
    DomainEventStream eventsByCRAsId(String crasId);
}
