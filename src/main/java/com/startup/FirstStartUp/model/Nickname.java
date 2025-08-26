package com.startup.FirstStartUp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nicknames")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nickname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String description;
}