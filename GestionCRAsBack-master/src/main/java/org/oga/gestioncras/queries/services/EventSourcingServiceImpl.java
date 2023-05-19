package org.oga.gestioncras.queries.services;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

@Service
public class EventSourcingServiceImpl implements EventSourcingService {
    private final EventStore eventStore;

    public EventSourcingServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }
    @Override
    public DomainEventStream eventsByCRAsId(String crasId){
        DomainEventStream domainEventStream=eventStore.readEvents(crasId);
        return domainEventStream;
    }
}
