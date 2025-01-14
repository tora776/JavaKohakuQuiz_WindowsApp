package com.quiz.kohaku.controller;


import java.util.List;

public class Form {
    private List<String> answers;
    private List<Boolean> corrections;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

	public List<Boolean> getCorrections() {
		return corrections;
	}

	public void setCorrections(List<Boolean> corrections) {
		this.corrections = corrections;
	}
    
    

}