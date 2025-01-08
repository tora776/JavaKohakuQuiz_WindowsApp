package com.quiz.kohaku.util;

import java.util.ArrayList;
import java.util.List;

import com.quiz.kohaku.model.Quiz;

public class Util {
	public List<Quiz> makeQuizList() {
		// return用の空のListを用意
		List<Quiz> quizList = new ArrayList<Quiz>();
		// クイズの個数
		int quizCount = 9;
		// データをlistにまとめる
		for(int i = 0; i < quizCount; i++) {
			Quiz quiz = new Quiz();
			quiz.setQuiz("2024年紅白歌合戦の総合司会は誰？");
			quizList.add(quiz);
		}
		return quizList;
	}
}
