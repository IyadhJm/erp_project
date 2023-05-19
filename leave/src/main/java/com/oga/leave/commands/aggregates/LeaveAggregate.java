package com.oga.leave.commands.aggregates;

import com.oga.leave.commands.commands.*;
import com.oga.leave.enums.LeaveStatus;
import com.oga.leave.events.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


import java.time.LocalDate;

@Aggregate
@Slf4j
public class LeaveAggregate {
    @AggregateIdentifier
    private String leaveId;
    private String collaboraterId;
    private String humanResourcesManagerId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private double duration;
    private String reason;
    private double leaveBalance;
    private String notes;

    private String status;

    public LeaveAggregate() {
        // Required by AXON
    }
    @CommandHandler
    public LeaveAggregate(CreateLeaveCommand createLeaveCommand) {
        log.info("CreateLeave Received");
        AggregateLifecycle.apply(new LeaveCreatedEvent(
           createLeaveCommand.getId(),
           createLeaveCommand.getCollaboraterId(),
           createLeaveCommand.getHumanResourcesManagerId(),
           createLeaveCommand.getLeaveType(),
           createLeaveCommand.getStartDate(),
           createLeaveCommand.getEndDate(),
           createLeaveCommand.getDuration(),
           createLeaveCommand.getReason(),
           createLeaveCommand.getLeaveBalance(),
           createLeaveCommand.getNotes(),
                LeaveStatus.CREATED
        ));
    }
    @EventSourcingHandler
    public void on(LeaveCreatedEvent leaveCreatedEvent){
        log.info("LeaveCreatedEvent Occurred");
        this.leaveId=leaveCreatedEvent.getId();
        this.collaboraterId=leaveCreatedEvent.getCollaboraterId();
        this.humanResourcesManagerId=leaveCreatedEvent.getHumanResourcesManagerId();
        this.leaveType=leaveCreatedEvent.getLeaveType();
        this.startDate=leaveCreatedEvent.getStartDate();
        this.endDate=leaveCreatedEvent.getEndDate();
        this.duration=leaveCreatedEvent.getDuration();
        this.reason=leaveCreatedEvent.getReason();
        this.leaveBalance=leaveCreatedEvent.getLeaveBalance();
        this.notes=leaveCreatedEvent.getNotes();
        this.status=String.valueOf(leaveCreatedEvent.getStatus());
    }
    @CommandHandler
    public void handle (UpdateLeaveCommand updateLeaveCommand) {
        if ((updateLeaveCommand.getCollaboraterId() == null) || (updateLeaveCommand.getHumanResourcesManagerId() == null)) {
            throw new RuntimeException("Input should not be null");
        }
        AggregateLifecycle.apply(new LeaveUpdatedEvent(
                updateLeaveCommand.getId(),
                updateLeaveCommand.getCollaboraterId(),
                updateLeaveCommand.getHumanResourcesManagerId(),
                updateLeaveCommand.getLeaveType(),
                updateLeaveCommand.getStartDate(),
                updateLeaveCommand.getEndDate(),
                updateLeaveCommand.getDuration(),
                updateLeaveCommand.getReason(),
                updateLeaveCommand.getLeaveBalance(),
                updateLeaveCommand.getNotes(),
                LeaveStatus.UPDATED
        ));
    }
    @EventSourcingHandler
    public void on(LeaveUpdatedEvent leaveUpdatedEvent){
        log.info("LeaveUpdatedEvent occurred");
        this.leaveId=leaveUpdatedEvent.getId();
        this.collaboraterId=leaveUpdatedEvent.getCollaboraterId();
        this.humanResourcesManagerId=leaveUpdatedEvent.getHumanResourcesManagerId();
        this.leaveType=leaveUpdatedEvent.getLeaveType();
        this.startDate=leaveUpdatedEvent.getStartDate();
        this.endDate=leaveUpdatedEvent.getEndDate();
        this.duration=leaveUpdatedEvent.getDuration();
        this.reason=leaveUpdatedEvent.getReason();
        this.leaveBalance=leaveUpdatedEvent.getLeaveBalance();
        this.notes=leaveUpdatedEvent.getNotes();
        this.status=String.valueOf(leaveUpdatedEvent.getStatus());
    }
    @CommandHandler
    public void handle(ApprovedLeaveCommand approvedLeaveCommand) {
        AggregateLifecycle.apply(new LeaveApprovedEvent(
                approvedLeaveCommand.getId(),
                approvedLeaveCommand.getCollaboraterId(),
                approvedLeaveCommand.getHumanResourcesManagerId(),
                approvedLeaveCommand.getLeaveType(),
                approvedLeaveCommand.getStartDate(),
                approvedLeaveCommand.getEndDate(),
                approvedLeaveCommand.getDuration(),
                approvedLeaveCommand.getReason(),
                approvedLeaveCommand.getLeaveBalance(),
                approvedLeaveCommand.getNotes(),
                LeaveStatus.APPROVED
        ));
    }
    @EventSourcingHandler
    public void on(LeaveApprovedEvent leaveApprovedEvent){
        log.info("LeaveApprovedEvent occurred");
        this.leaveId=leaveApprovedEvent.getId();
        this.collaboraterId=leaveApprovedEvent.getCollaboraterId();
        this.humanResourcesManagerId=leaveApprovedEvent.getHumanResourcesManagerId();
        this.leaveType=leaveApprovedEvent.getLeaveType();
        this.startDate=leaveApprovedEvent.getStartDate();
        this.endDate=leaveApprovedEvent.getEndDate();
        this.duration=leaveApprovedEvent.getDuration();
        this.reason=leaveApprovedEvent.getReason();
        this.leaveBalance=leaveApprovedEvent.getLeaveBalance();
        this.notes=leaveApprovedEvent.getNotes();
        this.status=String.valueOf(leaveApprovedEvent.getStatus());
    }
    @CommandHandler
    public void handle(RejectedLeaveCommand rejectedLeaveCommand) {
        AggregateLifecycle.apply(new LeaveRejectedEvent(
                rejectedLeaveCommand.getId(),
                rejectedLeaveCommand.getCollaboraterId(),
                rejectedLeaveCommand.getHumanResourcesManagerId(),
                rejectedLeaveCommand.getLeaveType(),
                rejectedLeaveCommand.getStartDate(),
                rejectedLeaveCommand.getEndDate(),
                rejectedLeaveCommand.getDuration(),
                rejectedLeaveCommand.getReason(),
                rejectedLeaveCommand.getLeaveBalance(),
                rejectedLeaveCommand.getNotes(),
                LeaveStatus.REJECTED
        ));
    }
    @EventSourcingHandler
    public void on(LeaveRejectedEvent leaveRejectedEvent){
        log.info("LeaveRejectedEvent occurred");
        this.leaveId=leaveRejectedEvent.getId();
        this.collaboraterId=leaveRejectedEvent.getCollaboraterId();
        this.humanResourcesManagerId=leaveRejectedEvent.getHumanResourcesManagerId();
        this.leaveType=leaveRejectedEvent.getLeaveType();
        this.startDate=leaveRejectedEvent.getStartDate();
        this.endDate=leaveRejectedEvent.getEndDate();
        this.duration=leaveRejectedEvent.getDuration();
        this.reason=leaveRejectedEvent.getReason();
        this.leaveBalance=leaveRejectedEvent.getLeaveBalance();
        this.notes=leaveRejectedEvent.getNotes();
        this.status=String.valueOf(leaveRejectedEvent.getStatus());
    }







}
