package com.oga.leave.commands.services;

import com.oga.leave.commands.commands.ApprovedLeaveCommand;
import com.oga.leave.commands.commands.CreateLeaveCommand;
import com.oga.leave.commands.commands.RejectedLeaveCommand;
import com.oga.leave.commands.commands.UpdateLeaveCommand;
import com.oga.leave.commands.dtos.LeaveRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class LeaveCommandServiceImpl implements LeaveCommandService{
    @Autowired
    private CommandGateway commandGateway;
    @Override
    public CompletableFuture<String> createLeave(LeaveRequestDTO leaveRequestDTO) {
        return commandGateway.send(new CreateLeaveCommand(UUID.randomUUID().toString(),leaveRequestDTO.getCollaboraterId(),leaveRequestDTO.getHumanResourcesManagerId(),
        leaveRequestDTO.getLeaveType(),leaveRequestDTO.getStartDate(),leaveRequestDTO.getEndDate(),
                leaveRequestDTO.getDuration(),leaveRequestDTO.getReason(),leaveRequestDTO.getLeaveBalance(),
                leaveRequestDTO.getNotes()));
    }
    @Override
    public CompletableFuture<String> updateLeave(LeaveRequestDTO leaveRequestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new UpdateLeaveCommand(
         leaveRequestDTO.getLeaveId(),
         leaveRequestDTO.getCollaboraterId(),
         leaveRequestDTO.getHumanResourcesManagerId(),
         leaveRequestDTO.getLeaveType(),
         leaveRequestDTO.getStartDate(),
         leaveRequestDTO.getEndDate(),
         leaveRequestDTO.getDuration(),
         leaveRequestDTO.getReason(),
         leaveRequestDTO.getLeaveBalance(),
         leaveRequestDTO.getNotes()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update Leave" + ex.getMessage());
        });
    }
    @Override
    public CompletableFuture<String> approveLeave(LeaveRequestDTO leaveRequestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new ApprovedLeaveCommand(
            leaveRequestDTO.getLeaveId(),
            leaveRequestDTO.getCollaboraterId(),
            leaveRequestDTO.getHumanResourcesManagerId(),
            leaveRequestDTO.getLeaveType(),
            leaveRequestDTO.getStartDate(),
            leaveRequestDTO.getEndDate(),
            leaveRequestDTO.getDuration(),
            leaveRequestDTO.getReason(),
            leaveRequestDTO.getLeaveBalance(),
            leaveRequestDTO.getNotes()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update Leave" + ex.getMessage());
        });
    }
    @Override
    public CompletableFuture<String> rejectLeave(LeaveRequestDTO leaveRequestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new RejectedLeaveCommand(
            leaveRequestDTO.getLeaveId(),
            leaveRequestDTO.getCollaboraterId(),
            leaveRequestDTO.getHumanResourcesManagerId(),
            leaveRequestDTO.getLeaveType(),
            leaveRequestDTO.getStartDate(),
            leaveRequestDTO.getEndDate(),
            leaveRequestDTO.getDuration(),
            leaveRequestDTO.getReason(),
            leaveRequestDTO.getLeaveBalance(),
            leaveRequestDTO.getNotes()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to reject Leave" + ex.getMessage());
        });
    }

}
