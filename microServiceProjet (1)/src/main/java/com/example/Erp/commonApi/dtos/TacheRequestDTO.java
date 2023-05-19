package com.example.Erp.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TacheRequestDTO {
    private String tacheId;
    private String tacheTitle;
    private String tacheDescription;

    private String collaborator;
    private String responsible;
    private LocalDate startDate;
    private LocalDate endDate;
    private String project;

}
