package com.oga.leave.queries.services;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;

public interface EventSourcingService {
    DomainEventStream eventsByLeaveId(String leaveId);
}
