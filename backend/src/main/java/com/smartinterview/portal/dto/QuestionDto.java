package com.smartinterview.portal.dto;

public record QuestionDto(
        Long id,
        String title,
        String answer,
        String topic,
        String difficulty,
        String role,
        String company,
        Boolean frequentlyAsked,
        boolean favorite,
        boolean completed
) {}
