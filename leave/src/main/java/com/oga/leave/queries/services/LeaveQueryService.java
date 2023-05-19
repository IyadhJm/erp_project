package com.oga.leave.queries.services;

import com.oga.leave.enums.LeaveStatus;
import com.oga.leave.events.LeaveCreatedEvent;
import com.oga.leave.mappers.LeaveMapper;
import com.oga.leave.queries.controllers.GetLeaveQuery;
import com.oga.leave.queries.dtos.GetAllLeavesRequestDTO;
import com.oga.leave.queries.dtos.LeaveResponseDTO;
import com.oga.leave.queries.entities.Leave;
import com.oga.leave.queries.repositories.LeaveRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveQueryService {
    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;
    private final QueryUpdateEmitter queryUpdateEmitter;
    public LeaveQueryService(LeaveRepository leaveRepository, LeaveMapper leaveMapper, QueryUpdateEmitter queryUpdateEmitter) {
        this.leaveRepository = leaveRepository;
        this.leaveMapper = leaveMapper;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }
    @EventHandler

    public void on(LeaveCreatedEvent leaveCreatedEvent) {
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

    }
    @QueryHandler
    public List<Leave> on (GetLeaveQuery query){ return leaveRepository.findAll(); }

    @QueryHandler
    public List<LeaveResponseDTO> on(GetAllLeavesRequestDTO leavesRequestDTO) {
        List<Leave> leaveList = leaveRepository.findAll();
        return leaveList.stream().map((app -> leaveMapper.leaveToLeaveDTO(app))).collect(Collectors.toList());
    }
}
