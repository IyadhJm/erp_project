package com.oga.leave.queries.repositories;

import com.oga.leave.enums.LeaveStatus;
import com.oga.leave.queries.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave,String> {
    List<Leave> findLeaveByStatus(LeaveStatus status);
}
