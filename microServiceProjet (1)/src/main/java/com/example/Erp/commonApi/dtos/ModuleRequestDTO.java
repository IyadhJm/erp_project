package com.example.Erp.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ModuleRequestDTO {
    private String moduleId;
    private String moduleTitle;
    private String moduleDescription;

    private String userId;
    private String project;

}
