package com.quiz.kohaku.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quiz.kohaku.model.Result;

@Repository
public class ResultDao implements IResultDao{
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ResultDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Result> resultList() {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM result");
		String sql = sqlBuilder.toString();
		
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		// タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		// return用の空のListを用意
		List<Result> list = new ArrayList<Result>();
		
		// データをlistにまとめる
		for(Map<String, Object>result : resultList) {
			Result resultKohaku = new Result();
			resultKohaku.setYear(result.get("year") != null ? (int)result.get("year") : 0);
			resultKohaku.setWinner(result.get("winner") != null ? (String)result.get("winner") : "");
			list.add(resultKohaku);
		}
		
		return list;
	}
}
