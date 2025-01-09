package com.quiz.kohaku.controller;

import java.util.ArrayList;
import java.util.List;

public class Form {
    private List<String> answers = new ArrayList<>();

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}