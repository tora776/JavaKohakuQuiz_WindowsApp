package com.quiz.kohaku.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	@Override
	public String getCorrectAnswer(int caseNumber, String... strings) {
		StringBuilder sqlBuilder = new StringBuilder();
		String sql;
		switch(caseNumber) {
			case 3: // {アーティスト名}が紅白で初めて歌った楽曲は何？
				// 本来はappearance = 1が望ましいが、紅白歌合戦に関する全データを格納できていないため、appearanceが最小のときの楽曲で妥協している。 
				sqlBuilder.append("SELECT artist_song FROM artist WHERE artist_name = ? AND appearance = ( SELECT MIN(appearance) FROM artist WHERE artist_name = ?)");
				sql = sqlBuilder.toString();
				// DBより正解を取得
				try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kohaku_uta_gassen", "dbo", "2jfq9n1j");
					PreparedStatement ps = conn.prepareStatement(sql);){
					ps.setString(1, strings[0]);
					ps.setString(2, strings[0]);
					ResultSet result = ps.executeQuery();
					if (result.next()) {
						return result.getString("artist_song");
					}
				} catch(SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			
			case 5: // {アーティスト名}は{曲名}を何回歌っている？
				// クエリを作成
				sqlBuilder.append("SELECT COUNT(artist_song) FROM artist WHERE artist_name = ? AND artist_song = ?;");
				sql = sqlBuilder.toString();
				// DBより正解を取得
				try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kohaku_uta_gassen", "dbo", "2jfq9n1j");
					PreparedStatement ps = conn.prepareStatement(sql);){
					ps.setString(1, strings[0]);
					ps.setString(2, strings[1]);
					ResultSet result = ps.executeQuery();
					if (result.next()) {
						return result.getString("count");
					}
				} catch(SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				
		}
		
		
		return "";

		/*
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		// タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		
		
		// データをlistにまとめる
		for(Map<String, Object>result : resultList) {
			Quiz quiz = new Quiz();
			quiz.setCorrectAnswer(result.get("") != null ? (String)result.get("quiz_id") : ""); // result.get箇所を記載する。case文で分けること
			list.add(quiz);
		}
		*/
		
		// return list;
	}


}
