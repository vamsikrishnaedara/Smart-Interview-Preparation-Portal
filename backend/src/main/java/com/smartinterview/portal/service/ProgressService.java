package com.smartinterview.portal.service;

import com.smartinterview.portal.dto.ProgressDto;
import com.smartinterview.portal.entity.CompletedQuestion;
import com.smartinterview.portal.entity.User;
import com.smartinterview.portal.repository.CompletedQuestionRepository;
import com.smartinterview.portal.repository.FavoriteRepository;
import com.smartinterview.portal.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final QuestionRepository questionRepository;
    private final CompletedQuestionRepository completedQuestionRepository;
    private final FavoriteRepository favoriteRepository;
    private final AppUserService appUserService;

    public ProgressDto getProgress() {
        User user = appUserService.currentUser();
        long total = questionRepository.count();
        long completed = completedQuestionRepository.countByUser(user);
        long favorites = favoriteRepository.countByUser(user);
        long remaining = Math.max(total - completed, 0);
        double percentage = total == 0 ? 0 : (completed * 100.0) / total;

        Map<String, Long> topic = completedQuestionRepository.findByUser(user).stream()
                .map(CompletedQuestion::getQuestion)
                .collect(Collectors.groupingBy(q -> q.getTopic() == null ? "Other" : q.getTopic(), Collectors.counting()));

        Map<String, Long> role = completedQuestionRepository.findByUser(user).stream()
                .map(CompletedQuestion::getQuestion)
                .collect(Collectors.groupingBy(q -> q.getRole() == null ? "Other" : q.getRole(), Collectors.counting()));

        return new ProgressDto(total, completed, remaining, favorites, percentage, topic, role);
    }
}
