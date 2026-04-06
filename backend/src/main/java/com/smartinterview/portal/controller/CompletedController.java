package com.smartinterview.portal.controller;

import com.smartinterview.portal.dto.ProgressDto;
import com.smartinterview.portal.dto.QuestionDto;
import com.smartinterview.portal.service.CompletedService;
import com.smartinterview.portal.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompletedController {
    private final CompletedService completedService;
    private final ProgressService progressService;

    @PostMapping("/completed/{questionId}")
    public void add(@PathVariable Long questionId) { completedService.add(questionId); }

    @DeleteMapping("/completed/{questionId}")
    public void remove(@PathVariable Long questionId) { completedService.remove(questionId); }

    @GetMapping("/completed")
    public List<QuestionDto> getAll() { return completedService.getAll(); }

    @GetMapping("/progress")
    public ProgressDto progress() { return progressService.getProgress(); }
}
