package com.startup.FirstStartUp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String title;

    @Column(unique = true)
    private String slug;

    private String category;

    private LocalDateTime createdAt = LocalDateTime.now();
}