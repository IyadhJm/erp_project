package com.example.notification.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notifications {
    @Id
    private String id = UUID.randomUUID().toString();
    private String message;
    private String collaborator;
    private String responsible;
}
