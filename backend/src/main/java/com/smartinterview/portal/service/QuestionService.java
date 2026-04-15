package com.smartinterview.portal.service;

import com.smartinterview.portal.dto.QuestionDto;
import com.smartinterview.portal.entity.Question;
import com.smartinterview.portal.entity.User;
import com.smartinterview.portal.repository.CompletedQuestionRepository;
import com.smartinterview.portal.repository.FavoriteRepository;
import com.smartinterview.portal.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final FavoriteRepository favoriteRepository;
    private final CompletedQuestionRepository completedQuestionRepository;
    private final AppUserService appUserService;

    public List<QuestionDto> all() { return map(questionRepository::findAll); }
    public List<QuestionDto> byRole(String role) { return map(() -> questionRepository.findByRoleIgnoreCase(role)); }
    public List<QuestionDto> byTopic(String topic) { return map(() -> questionRepository.findByTopicIgnoreCase(topic)); }
    public List<QuestionDto> byDifficulty(String difficulty) { return map(() -> questionRepository.findByDifficultyIgnoreCase(difficulty)); }
    public List<QuestionDto> search(String keyword) {
        return map(() -> questionRepository.findByTitleContainingIgnoreCaseOrAnswerContainingIgnoreCase(keyword, keyword));
    }

    private List<QuestionDto> map(Supplier<List<Question>> supplier) {
        User user = appUserService.currentUser();
        List<Question> questions = supplier.get();
        
        Set<Long> favoriteIds = favoriteRepository.findByUser(user).stream()
                .map(f -> f.getQuestion().getId()).collect(Collectors.toSet());
        Set<Long> completedIds = completedQuestionRepository.findByUser(user).stream()
                .map(c -> c.getQuestion().getId()).collect(Collectors.toSet());

        return questions.stream().map(q -> new QuestionDto(
                q.getId(), q.getTitle(), q.getAnswer(), q.getTopic(), q.getDifficulty(), q.getRole(),
                q.getCompany(), q.getFrequentlyAsked(),
                favoriteIds.contains(q.getId()),
                completedIds.contains(q.getId())
        )).toList();
    }
}
