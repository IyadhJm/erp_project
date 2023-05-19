package com.example.Erp.query.entities;

import com.example.Erp.commonApi.enums.moduleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    @Id
    private String moduleId;
    private String moduleTitle;
    private String moduleDescription;
    private String userId;
    @Enumerated(EnumType.STRING)
    private moduleStatus status;
    @JsonBackReference

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private Project project;
}
