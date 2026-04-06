package com.smartinterview.portal.dto;

import java.util.Map;

public record ProgressDto(
        long totalQuestions,
        long completedQuestions,
        long remainingQuestions,
        long favoriteQuestions,
        double completionPercentage,
        Map<String, Long> topicWiseCompleted,
        Map<String, Long> roleWiseCompleted
) {}
