package com.example.Erp.query.entities;

import com.example.Erp.commonApi.enums.projectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    private String id;
    private String projectTitle;
    private String projectDescription;
    @JsonIgnore
    @ElementCollection
    private List<String> collaborators = new ArrayList<>();


    private String startDate;

    private String endDate;
    @Enumerated(EnumType.STRING) 
    private projectStatus status;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.PERSIST)
    private Collection<Tache> taches;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.PERSIST)
    private Collection<Branche> branches;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.PERSIST)
    private List<Module> modules = new ArrayList<>();

}
