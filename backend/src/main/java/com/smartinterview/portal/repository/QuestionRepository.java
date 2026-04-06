package com.smartinterview.portal.repository;

import com.smartinterview.portal.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByRoleIgnoreCase(String role);
    List<Question> findByTopicIgnoreCase(String topic);
    List<Question> findByDifficultyIgnoreCase(String difficulty);
    List<Question> findByTitleContainingIgnoreCaseOrAnswerContainingIgnoreCase(String title, String answer);
}
