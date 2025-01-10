package com.quiz.kohaku.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quiz.kohaku.model.Quiz;

@Repository
public class QuizDao implements IQuizDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public QuizDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public List<Quiz> quizList() {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM quiz");
		String sql = sqlBuilder.toString();
		
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		// タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		// return用の空のListを用意
		List<Quiz> list = new ArrayList<Quiz>();
		
		// データをlistにまとめる
		for(Map<String, Object>result : resultList) {
			Quiz quiz = new Quiz();
			quiz.setQuiz_id(result.get("quiz_id") != null ? (int)result.get("quiz_id") : 0);
			quiz.setQuiz_template(result.get("quiz_template") != null ? (String)result.get("quiz_template") : "");
			quiz.setQuiz_category(result.get("quiz_category") != null ? (String)result.get("quiz_category") : "");
			quiz.setAnswer1(result.get("answer1") != null ? (String)result.get("answer1") : "");
			quiz.setAnswer2(result.get("answer2") != null ? (String)result.get("answer2") : "");
			quiz.setAnswer3(result.get("answer3") != null ? (String)result.get("answer3") : "");
			quiz.setAnswer4(result.get("answer4") != null ? (String)result.get("answer4") : "");
			quiz.setCreated_date(result.get("created_date") != null ? (String)result.get("created_date").toString() : "");
			quiz.setUpdated_date(result.get("updated_date") != null ? (String)result.get("updated_date").toString() : "");
			list.add(quiz);
		}
		
		return list;
	}


}
