package com.example.vocabflow.controller;

import com.example.vocabflow.entity.Word;
import com.example.vocabflow.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
@CrossOrigin(origins = "*")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable Long id) {

        return wordService.getWordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Word> searchWords(@RequestParam String query) {
        return wordService.searchWords(query);
    }
}