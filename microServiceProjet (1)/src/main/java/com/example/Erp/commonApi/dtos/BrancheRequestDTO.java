package com.example.Erp.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BrancheRequestDTO {
    private String brancheId;
    private String branchTitle;
    private String branchDescription;

    private String userId;
    private String project;

}
