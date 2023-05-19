package com.example.Erp.commonApi.queries.BranchQueries;

import com.example.Erp.query.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetByProject {
    private Project project;

}
