package com.smartinterview.portal.repository;

import com.smartinterview.portal.entity.Favorite;
import com.smartinterview.portal.entity.Question;
import com.smartinterview.portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndQuestion(User user, Question question);
    long countByUser(User user);
}
