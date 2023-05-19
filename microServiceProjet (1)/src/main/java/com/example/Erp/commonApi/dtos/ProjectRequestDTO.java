package com.example.Erp.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProjectRequestDTO {
    private String id;
    private String projectTitle;
    private String projectDescription;

    private List<String> collaborators;
    private LocalDate startDate;
    private LocalDate endDate;

}
