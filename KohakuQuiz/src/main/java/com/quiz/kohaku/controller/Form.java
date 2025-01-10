package com.quiz.kohaku.controller;


import java.util.List;

import com.quiz.kohaku.model.Quiz;

public class Form {
    private List<String> answers;
    private List<Quiz> quizList;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    
    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
}