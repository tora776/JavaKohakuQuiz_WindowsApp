package com.quiz.kohaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.kohaku.model.Quiz;
import com.quiz.kohaku.repository.IQuizDao;

@Service
@Transactional
public class QuizService {
	private final IQuizDao dao;

	@Autowired
	public QuizService(IQuizDao dao) {
		this.dao = dao;
	}
	
	public List<Quiz> quizList(){
		return dao.quizList();
	}
	
	public String getArtistCorrectAnswer(int caseNumber, String... strings ) {
	    return dao.getArtistCorrectAnswer(caseNumber, strings);
	}
	
	
}
