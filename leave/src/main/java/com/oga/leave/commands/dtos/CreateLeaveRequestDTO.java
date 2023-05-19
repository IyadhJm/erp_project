package com.oga.leave.commands.dtos;

import lombok.Data;


import java.time.LocalDate;

@Data
public class CreateLeaveRequestDTO {

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
