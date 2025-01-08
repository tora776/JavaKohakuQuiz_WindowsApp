package com.quiz.kohaku.controller;

import java.util.List;

public class Form {
    private List<String> answers; // 複数回答を保持するリスト

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}