package com.quiz.kohaku.repository;

import java.util.List;

import com.quiz.kohaku.model.Quiz;

public interface IQuizDao {
	// DBのアーティスト名を取得
	List<Quiz> quizList();
}
