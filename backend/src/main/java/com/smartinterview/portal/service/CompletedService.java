package com.smartinterview.portal.service;

import com.smartinterview.portal.dto.QuestionDto;
import com.smartinterview.portal.entity.CompletedQuestion;
import com.smartinterview.portal.entity.Question;
import com.smartinterview.portal.entity.User;
import com.smartinterview.portal.repository.CompletedQuestionRepository;
import com.smartinterview.portal.repository.FavoriteRepository;
import com.smartinterview.portal.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletedService {
    private final CompletedQuestionRepository completedQuestionRepository;
    private final QuestionRepository questionRepository;
    private final FavoriteRepository favoriteRepository;
    private final AppUserService appUserService;

    public void add(Long questionId) {
        User user = appUserService.currentUser();
        Question question = questionRepository.findById(questionId).orElseThrow();
        completedQuestionRepository.findByUserAndQuestion(user, question).orElseGet(() ->
                completedQuestionRepository.save(CompletedQuestion.builder().user(user).question(question).build()));
    }

    public void remove(Long questionId) {
        User user = appUserService.currentUser();
        Question question = questionRepository.findById(questionId).orElseThrow();
        completedQuestionRepository.findByUserAndQuestion(user, question).ifPresent(completedQuestionRepository::delete);
    }

    public List<QuestionDto> getAll() {
        User user = appUserService.currentUser();
        return completedQuestionRepository.findByUser(user).stream().map(CompletedQuestion::getQuestion).map(q -> new QuestionDto(
                q.getId(), q.getTitle(), q.getAnswer(), q.getTopic(), q.getDifficulty(), q.getRole(),
                q.getCompany(), q.getFrequentlyAsked(),
                favoriteRepository.findByUserAndQuestion(user, q).isPresent(), true
        )).toList();
    }
}
