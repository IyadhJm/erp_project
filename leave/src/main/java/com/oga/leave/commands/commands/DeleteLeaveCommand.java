package com.oga.leave.commands.commands;

import lombok.Getter;

import java.time.LocalDate;

public class DeleteLeaveCommand extends BaseCommand<String>{
    @Getter private String collaboraterId;
    @Getter private String humanResourcesManagerId;
    @Getter private String leaveType;
    @Getter private LocalDate startDate;
    @Getter private LocalDate endDate;
    @Getter private double duration;
    @Getter private String reason;
    @Getter private double leaveBalance;
    @Getter private String notes;

    public DeleteLeaveCommand(String id, String collaboraterId, String humanResourcesManagerId, String leaveType, LocalDate startDate, LocalDate endDate, double duration, String reason, double leaveBalance, String notes) {
        super(id);
        this.collaboraterId = collaboraterId;
        this.humanResourcesManagerId = humanResourcesManagerId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.reason = reason;
        this.leaveBalance = leaveBalance;
        this.notes = notes;
    }
}
