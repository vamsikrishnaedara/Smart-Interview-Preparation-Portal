package com.smartinterview.portal.repository;

import com.smartinterview.portal.entity.CompletedQuestion;
import com.smartinterview.portal.entity.Question;
import com.smartinterview.portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompletedQuestionRepository extends JpaRepository<CompletedQuestion, Long> {
    List<CompletedQuestion> findByUser(User user);
    Optional<CompletedQuestion> findByUserAndQuestion(User user, Question question);
    long countByUser(User user);
}
