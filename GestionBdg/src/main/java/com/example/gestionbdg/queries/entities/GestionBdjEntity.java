package com.example.gestionbdg.queries.entities;


import com.example.gestionbdg.enums.GbdgStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionBdjEntity {
    @Id
    private String bdgId;


    @ElementCollection
    private List<String> userName;

    private String collaboratorId;
    private Double tjm;
    private Double cjm;
    @Enumerated(EnumType.STRING)
    private GbdgStatus status;
}
