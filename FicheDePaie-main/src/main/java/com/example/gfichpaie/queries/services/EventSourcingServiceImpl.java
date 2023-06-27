package com.example.gfichpaie.queries.services;

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
    public DomainEventStream eventsByFDPId(String fdpId){
        return eventStore.readEvents(fdpId);
    }
}
