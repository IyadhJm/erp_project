package com.oga.leave.queries.entities;

import com.oga.leave.enums.LeaveStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Leave {
    @Id
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
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

}
