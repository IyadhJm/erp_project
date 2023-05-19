package com.oga.leave.commands.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDTO {
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
}
