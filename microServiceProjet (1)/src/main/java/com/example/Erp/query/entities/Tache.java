package com.example.Erp.query.entities;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tache {
    @Id
    private String tacheId;
    private String tacheTitle;
    private String tacheDescription;

    private String collaborator;
    private  String responsible;
    private LocalDate startDate;
    private LocalDate endDate;


    @Enumerated(EnumType.STRING)
    private tacheStatus status;
    @JsonBackReference

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private Project project;

}
