package com.startup.FirstStartUp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sliders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
    private String buttonText;
    private String buttonLink;
}