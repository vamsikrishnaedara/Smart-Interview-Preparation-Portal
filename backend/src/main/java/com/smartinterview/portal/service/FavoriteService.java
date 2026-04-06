package com.smartinterview.portal.service;

import com.smartinterview.portal.dto.QuestionDto;
import com.smartinterview.portal.entity.Favorite;
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
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final QuestionRepository questionRepository;
    private final CompletedQuestionRepository completedQuestionRepository;
    private final AppUserService appUserService;

    public void add(Long questionId) {
        User user = appUserService.currentUser();
        Question question = questionRepository.findById(questionId).orElseThrow();
        favoriteRepository.findByUserAndQuestion(user, question).orElseGet(() ->
                favoriteRepository.save(Favorite.builder().user(user).question(question).build()));
    }

    public void remove(Long questionId) {
        User user = appUserService.currentUser();
        Question question = questionRepository.findById(questionId).orElseThrow();
        favoriteRepository.findByUserAndQuestion(user, question).ifPresent(favoriteRepository::delete);
    }

    public List<QuestionDto> getAll() {
        User user = appUserService.currentUser();
        return favoriteRepository.findByUser(user).stream().map(Favorite::getQuestion).map(q -> new QuestionDto(
                q.getId(), q.getTitle(), q.getAnswer(), q.getTopic(), q.getDifficulty(), q.getRole(),
                q.getCompany(), q.getFrequentlyAsked(), true,
                completedQuestionRepository.findByUserAndQuestion(user, q).isPresent()
        )).toList();
    }
}
