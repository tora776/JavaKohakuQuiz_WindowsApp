package com.quiz.kohaku.model;

public class Quiz {
	private String quiz; // Java内で使用。DB内にQuizカラムはない。
	private int quiz_id;
	private String quiz_template;
	private String quiz_category;
	private String choice_template1;
	private String choice_template2;
	private String choice_template3;
	private String choice_template4;
	private String created_date;
	private String updated_date;
	
	public String getChoice_template1() {
		return choice_template1;
	}

	public void setChoice_template1(String choice_template1) {
		this.choice_template1 = choice_template1;
	}

	public String getChoice_template2() {
		return choice_template2;
	}

	public void setChoice_template2(String choice_template2) {
		this.choice_template2 = choice_template2;
	}

	public String getChoice_template3() {
		return choice_template3;
	}

	public void setChoice_template3(String choice_template3) {
		this.choice_template3 = choice_template3;
	}

	public String getChoice_template4() {
		return choice_template4;
	}

	public void setChoice_template4(String choice_template4) {
		this.choice_template4 = choice_template4;
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
