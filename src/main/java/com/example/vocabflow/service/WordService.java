package com.example.vocabflow.service;

import com.example.vocabflow.entity.Word;
import com.example.vocabflow.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordById(Long id) {
        return wordRepository.findById(id);
    }

    public List<Word> searchWords(String query) {
        return wordRepository.findByWordContainingIgnoreCase(query);
    }
}