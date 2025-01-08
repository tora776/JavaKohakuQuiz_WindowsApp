package com.quiz.kohaku.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quiz.kohaku.model.Host;

@Repository
public class HostDao implements IHostDao {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
		
		@Autowired
		public HostDao(NamedParameterJdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

	@Override
	public List<Host> hostList() {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM host");
		String sql = sqlBuilder.toString();
		
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		// タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		// return用の空のListを用意
		List<Host> list = new ArrayList<Host>();
		
		// データをlistにまとめる
		for(Map<String, Object>result : resultList) {
			Host host = new Host();
			host.setParticipation_id(result.get("participation_id") != null ? (int)result.get("participation_id") : 0);
			host.setHost_name(result.get("host_name") != null ? (String)result.get("host_name") : "");
			host.setYear(result.get("year") != null ? (int)result.get("year") : 0);
			host.setAppearance(result.get("appearance") != null ? (int)result.get("appearance") : 0);
			host.setHost_role(result.get("host_role") != null ? (String)result.get("host_role") : "");
			list.add(host);
		}
		
		return list;
	}

}
