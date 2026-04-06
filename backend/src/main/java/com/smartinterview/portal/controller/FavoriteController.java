package com.smartinterview.portal.controller;

import com.smartinterview.portal.dto.QuestionDto;
import com.smartinterview.portal.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/{questionId}")
    public void add(@PathVariable Long questionId) { favoriteService.add(questionId); }

    @DeleteMapping("/{questionId}")
    public void remove(@PathVariable Long questionId) { favoriteService.remove(questionId); }

    @GetMapping
    public List<QuestionDto> getAll() { return favoriteService.getAll(); }
}
