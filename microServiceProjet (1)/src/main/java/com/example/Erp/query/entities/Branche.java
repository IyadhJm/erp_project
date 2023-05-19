package com.example.Erp.query.entities;

import com.example.Erp.commonApi.enums.branchStatus;
import com.example.Erp.commonApi.enums.moduleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Branche {
    @Id
    private String brancheId;
    private String branchTitle;
    private String branchDescription;
    private String userId;
    @Enumerated(EnumType.STRING)
    private branchStatus status;
    @JsonBackReference

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private Project project;


}
