package com.oga.leave.queries.services;

import com.oga.leave.enums.LeaveStatus;
import com.oga.leave.events.LeaveApprovedEvent;
import com.oga.leave.events.LeaveCreatedEvent;
import com.oga.leave.events.LeaveRejectedEvent;
import com.oga.leave.events.LeaveUpdatedEvent;
import com.oga.leave.queries.controllers.GetLeaveQuery;
import com.oga.leave.queries.entities.Leave;
import com.oga.leave.queries.kafkaProducer.ApprooveLeaveProducer;
import com.oga.leave.queries.kafkaProducer.LeaveProducer;
import com.oga.leave.queries.kafkaProducer.RejectedLeaveProducer;
import com.oga.leave.queries.repositories.LeaveRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LeaveServiceHandler {
    @Autowired
    private LeaveProducer leaveProducer;
    @Autowired
    private ApprooveLeaveProducer approoveLeaveProducer;
    @Autowired
    private RejectedLeaveProducer rejectedLeaveProducer;
    private LeaveRepository leaveRepository;
    private KafkaTemplate kafkaTemplate;
    @EventHandler
    public void on(LeaveCreatedEvent leaveCreatedEvent){
        log.info("*****");
        log.info("leaveCreatedEventReceived");
        Leave leave = new Leave();
        leave.setLeaveId(leaveCreatedEvent.getId());
        leave.setCollaboraterId(leaveCreatedEvent.getCollaboraterId());
        leave.setHumanResourcesManagerId(leaveCreatedEvent.getHumanResourcesManagerId());
        leave.setLeaveType(leaveCreatedEvent.getLeaveType());
        leave.setStartDate(leaveCreatedEvent.getStartDate());
        leave.setEndDate(leaveCreatedEvent.getEndDate());
        leave.setDuration(leaveCreatedEvent.getDuration());
        leave.setReason(leaveCreatedEvent.getReason());
        leave.setLeaveBalance(leaveCreatedEvent.getLeaveBalance());
        leave.setNotes(leaveCreatedEvent.getNotes());
        leave.setStatus(leaveCreatedEvent.getStatus());
        leaveRepository.save(leave);
        leaveProducer.sendMessage(leaveCreatedEvent);

    }

    @EventHandler
    public void on(LeaveUpdatedEvent leaveUpdatedEvent){
        log.info("*****");
        log.info("leaveUpdatedEventReceived");
        Leave leave = leaveRepository.findById(leaveUpdatedEvent.getId()).orElseThrow(()-> new IllegalArgumentException("Invalid Leave Id"));
        leave.setCollaboraterId(leaveUpdatedEvent.getCollaboraterId());
        leave.setHumanResourcesManagerId(leaveUpdatedEvent.getHumanResourcesManagerId());
        leave.setLeaveType(leaveUpdatedEvent.getLeaveType());
        leave.setStartDate(leaveUpdatedEvent.getStartDate());
        leave.setEndDate(leaveUpdatedEvent.getEndDate());
        leave.setDuration(leaveUpdatedEvent.getDuration());
        leave.setReason(leaveUpdatedEvent.getReason());
        leave.setLeaveBalance(leaveUpdatedEvent.getLeaveBalance());
        leave.setNotes(leaveUpdatedEvent.getNotes());
        leave.setStatus(leaveUpdatedEvent.getStatus());
        leaveRepository.save(leave);
    }
    @EventHandler
    public void on(LeaveApprovedEvent leaveApprovedEvent){
        log.info("*****");
        log.info("leaveUpdatedEventReceived");
        Leave leave = leaveRepository.findById(leaveApprovedEvent.getId()).orElseThrow(()-> new IllegalArgumentException("Invalid Leave Id"));
        leave.setCollaboraterId(leaveApprovedEvent.getCollaboraterId());
        leave.setHumanResourcesManagerId(leaveApprovedEvent.getHumanResourcesManagerId());
        leave.setLeaveType(leaveApprovedEvent.getLeaveType());
        leave.setStartDate(leaveApprovedEvent.getStartDate());
        leave.setEndDate(leaveApprovedEvent.getEndDate());
        leave.setDuration(leaveApprovedEvent.getDuration());
        leave.setReason(leaveApprovedEvent.getReason());
        leave.setLeaveBalance(leaveApprovedEvent.getLeaveBalance());
        leave.setNotes(leaveApprovedEvent.getNotes());
        leave.setStatus(leaveApprovedEvent.getStatus());
        leaveRepository.save(leave);
        approoveLeaveProducer.sendMessage(leaveApprovedEvent);

    }
    @EventHandler
    public void on(LeaveRejectedEvent leaveRejectedEvent){
        log.info("*****");
        log.info("leaveRejectedEventReceived");
        Leave leave = leaveRepository.findById(leaveRejectedEvent.getId()).orElseThrow(()-> new IllegalArgumentException("Invalid Leave Id"));
        leave.setCollaboraterId(leaveRejectedEvent.getCollaboraterId());
        leave.setHumanResourcesManagerId(leaveRejectedEvent.getHumanResourcesManagerId());
        leave.setLeaveType(leaveRejectedEvent.getLeaveType());
        leave.setStartDate(leaveRejectedEvent.getStartDate());
        leave.setEndDate(leaveRejectedEvent.getEndDate());
        leave.setDuration(leaveRejectedEvent.getDuration());
        leave.setReason(leaveRejectedEvent.getReason());
        leave.setLeaveBalance(leaveRejectedEvent.getLeaveBalance());
        leave.setNotes(leaveRejectedEvent.getNotes());
        leave.setStatus(leaveRejectedEvent.getStatus());
        leaveRepository.save(leave);
        rejectedLeaveProducer.sendMessage(leaveRejectedEvent);
    }
    @QueryHandler
    public List<Leave> on(GetLeaveQuery query){
        return leaveRepository.findAll();
    }


}
