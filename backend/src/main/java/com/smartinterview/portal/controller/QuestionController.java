package com.smartinterview.portal.controller;

import com.smartinterview.portal.dto.QuestionDto;
import com.smartinterview.portal.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public List<QuestionDto> all() { return questionService.all(); }
    @GetMapping("/role/{role}")
    public List<QuestionDto> byRole(@PathVariable String role) { return questionService.byRole(role); }
    @GetMapping("/topic/{topic}")
    public List<QuestionDto> byTopic(@PathVariable String topic) { return questionService.byTopic(topic); }
    @GetMapping("/difficulty/{level}")
    public List<QuestionDto> byDifficulty(@PathVariable String level) { return questionService.byDifficulty(level); }
    @GetMapping("/search")
    public List<QuestionDto> search(@RequestParam String keyword) { return questionService.search(keyword); }
}
