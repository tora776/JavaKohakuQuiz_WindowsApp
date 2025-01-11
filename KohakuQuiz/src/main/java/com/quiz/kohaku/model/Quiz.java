package com.quiz.kohaku.model;

public class Quiz {
	private String quiz; // Java内で使用。DB内にQuizカラムはない。
	private String correctAnswer; // クイズの正解。DB内にcorrectAnswerのカラムはない
	private int quiz_id;
	private String quiz_template;
	private String quiz_category;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String created_date;
	private String updated_date;
	
	
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	
	public String getQuiz() {
		return quiz;
	}

	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}

	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public String getQuiz_template() {
		return quiz_template;
	}

	public void setQuiz_template(String quiz_template) {
		this.quiz_template = quiz_template;
	}

	public String getQuiz_category() {
		return quiz_category;
	}

	public void setQuiz_category(String quiz_category) {
		this.quiz_category = quiz_category;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	
	
}
