package com.oga.leave.mappers;

import com.oga.leave.queries.dtos.LeaveResponseDTO;
import com.oga.leave.queries.entities.Leave;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface LeaveMapper {
    LeaveResponseDTO leaveToLeaveDTO(Leave leave);
}
