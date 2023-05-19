package com.oga.leave.mappers;

import com.oga.leave.queries.dtos.LeaveResponseDTO;
import com.oga.leave.queries.entities.Leave;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-18T13:37:32+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
@Component
public class LeaveMapperImpl implements LeaveMapper {

    @Override
    public LeaveResponseDTO leaveToLeaveDTO(Leave leave) {
        if ( leave == null ) {
            return null;
        }

        LeaveResponseDTO leaveResponseDTO = new LeaveResponseDTO();

        leaveResponseDTO.setLeaveId( leave.getLeaveId() );
        leaveResponseDTO.setCollaboraterId( leave.getCollaboraterId() );
        leaveResponseDTO.setHumanResourcesManagerId( leave.getHumanResourcesManagerId() );
        leaveResponseDTO.setLeaveType( leave.getLeaveType() );
        leaveResponseDTO.setStartDate( leave.getStartDate() );
        leaveResponseDTO.setEndDate( leave.getEndDate() );
        leaveResponseDTO.setDuration( leave.getDuration() );
        leaveResponseDTO.setReason( leave.getReason() );
        leaveResponseDTO.setLeaveBalance( leave.getLeaveBalance() );
        leaveResponseDTO.setNotes( leave.getNotes() );
        leaveResponseDTO.setStatus( leave.getStatus() );

        return leaveResponseDTO;
    }
}
