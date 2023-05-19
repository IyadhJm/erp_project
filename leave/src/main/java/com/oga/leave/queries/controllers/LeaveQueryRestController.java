package com.oga.leave.queries.controllers;

import com.oga.leave.queries.dtos.GetLeaveQueryDTO;
import com.oga.leave.queries.dtos.LeaveResponseDTO;
import com.oga.leave.queries.entities.Leave;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(path = "/query")
public class LeaveQueryRestController {
    private QueryGateway queryGateway;
    public LeaveQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;

    }
    @GetMapping(path ="/leaves/{id}")
    public LeaveResponseDTO getLeave(@PathVariable String id) {
        GetLeaveQueryDTO queryDTO = new GetLeaveQueryDTO();
        queryDTO.setLeaveId(id);
        return  queryGateway.query(queryDTO, LeaveResponseDTO.class).join();

    }
    @GetMapping()
    public List<Leave> leaveList(){
        List<Leave> response = queryGateway.query((new GetLeaveQuery()), ResponseTypes.multipleInstancesOf(Leave.class)).join();
        return response;
    }
   /* @GetMapping(value = "/{leaveId}/watch",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LeaveResponseDTO> watch(@PathVariable String leaveId){
        SubscriptionQueryResult<LeaveResponseDTO,LeaveResponseDTO> result=
                queryGateway.subscriptionQuery(
                        new GetLeaveQueryDTO(leaveId),
                        ResponseTypes.instanceOf(LeaveResponseDTO.class),
                        ResponseTypes.instanceOf(LeaveResponseDTO.class)
                );
        return result.initialResult().concatWith(result.updates());
    }*/

}
