package com.example.Erp.commonApi.queries.TacheQueries;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.query.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTacheByProject {
    private Project project;
    private tacheStatus status;
}
