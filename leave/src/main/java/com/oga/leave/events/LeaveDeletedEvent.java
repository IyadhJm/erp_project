package com.oga.leave.events;

import com.oga.leave.enums.LeaveStatus;
import lombok.Getter;

import java.time.LocalDate;

public class LeaveDeletedEvent extends BaseEvent<String>{

    @Getter private String collaboraterId;
    @Getter private String humanResourcesManagerId;
    @Getter private String leaveType;
    @Getter private LocalDate startDate;
    @Getter private LocalDate endDate;
    @Getter private double duration;
    @Getter private String reason;
    @Getter private double leaveBalance;
    @Getter private String notes;
    @Getter private LeaveStatus status;

    public LeaveDeletedEvent(String id, String collaboraterId, String humanResourcesManagerId, String leaveType, LocalDate startDate, LocalDate endDate, double duration, String reason, double leaveBalance, String notes, LeaveStatus status) {
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
        this.status = status;
    }
}
