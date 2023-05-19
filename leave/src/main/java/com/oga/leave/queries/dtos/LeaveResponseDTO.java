package com.oga.leave.queries.dtos;

import com.oga.leave.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Data
@AllArgsConstructor @NoArgsConstructor
public class LeaveResponseDTO {
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
    private LeaveStatus status;
}
