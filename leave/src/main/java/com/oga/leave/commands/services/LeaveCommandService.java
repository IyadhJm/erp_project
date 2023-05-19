package com.oga.leave.commands.services;

import com.oga.leave.commands.commands.UpdateLeaveCommand;
import com.oga.leave.commands.dtos.LeaveRequestDTO;
import com.oga.leave.commands.dtos.UpdateLeaveRequestDTO;

import java.util.concurrent.CompletableFuture;

public interface LeaveCommandService {
    CompletableFuture<String> createLeave(LeaveRequestDTO leaveRequestDTO);
    CompletableFuture<String> updateLeave( LeaveRequestDTO leaveRequestDTO);
    CompletableFuture<String> rejectLeave( LeaveRequestDTO leaveRequestDTO);
    CompletableFuture<String> approveLeave( LeaveRequestDTO leaveRequestDTO);


}
