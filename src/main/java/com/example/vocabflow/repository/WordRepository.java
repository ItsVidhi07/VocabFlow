package com.example.vocabflow.repository;

import com.example.vocabflow.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findByWordContainingIgnoreCase(String word);

}