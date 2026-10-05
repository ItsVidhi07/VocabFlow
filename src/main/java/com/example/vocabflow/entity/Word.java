package com.example.vocabflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;

    @Column(nullable = false)
    private String partOfSpeech;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String meaning;

    @Column(columnDefinition = "TEXT")
    private String exampleSentence;

    @Column(columnDefinition = "TEXT")
    private String synonyms;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private String category;

    // Default constructor required by JPA
    public Word() {
    }

    public Word(String word, String partOfSpeech, String meaning,
                String exampleSentence, String synonyms,
                String difficulty, String category) {

        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.meaning = meaning;
        this.exampleSentence = exampleSentence;
        this.synonyms = synonyms;
        this.difficulty = difficulty;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}