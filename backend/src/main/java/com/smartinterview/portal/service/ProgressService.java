package com.smartinterview.portal.service;

import com.smartinterview.portal.dto.ProgressDto;
import com.smartinterview.portal.entity.CompletedQuestion;
import com.smartinterview.portal.entity.User;
import com.smartinterview.portal.repository.CompletedQuestionRepository;
import com.smartinterview.portal.repository.FavoriteRepository;
import com.smartinterview.portal.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        
        List<CompletedQuestion> completedList = completedQuestionRepository.findByUser(user);
        long completed = completedList.size();
        long favorites = favoriteRepository.countByUser(user);
        long remaining = Math.max(total - completed, 0);
        double percentage = total == 0 ? 0 : (completed * 100.0) / total;

        // Optimized grouping to avoid N+1 by accessing question data already in the list
        Map<String, Long> topic = completedList.stream()
                .map(cq -> cq.getQuestion().getTopic() == null ? "Other" : cq.getQuestion().getTopic())
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()));

        Map<String, Long> role = completedList.stream()
                .map(cq -> cq.getQuestion().getRole() == null ? "Other" : cq.getQuestion().getRole())
                .collect(Collectors.groupingBy(r -> r, Collectors.counting()));

        return new ProgressDto(total, completed, remaining, favorites, percentage, topic, role);
    }
}
