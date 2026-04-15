package com.smartinterview.portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 300)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;
    private String topic;
    private String difficulty;
    private String role;
    private String company;
    @Builder.Default
    private Boolean frequentlyAsked = false;
}
