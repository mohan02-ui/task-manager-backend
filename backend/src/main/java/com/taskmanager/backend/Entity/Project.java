package com.taskmanager.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private User admin;
}
