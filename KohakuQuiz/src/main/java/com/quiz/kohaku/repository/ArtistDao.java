package com.quiz.kohaku.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quiz.kohaku.model.Artist;

@Repository
public class ArtistDao implements IArtistDao {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ArtistDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public List<Artist> artistList() {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM artist");
		String sql = sqlBuilder.toString();
		
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		// タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		// return用の空のListを用意
		List<Artist> list = new ArrayList<Artist>();
		
		// データをlistにまとめる
		for(Map<String, Object>result : resultList) {
			Artist artist = new Artist();
			artist.setParticipation_id(result.get("participation_id") != null ? (int)result.get("participation_id") : 0);
			artist.setArtist_name(result.get("artist_name") != null ? (String)result.get("artist_name") : "");
			artist.setArtist_song(result.get("artist_song") != null ? (String)result.get("artist_song") : "");
			artist.setYear(result.get("year") != null ? (int)result.get("year") : 0);
			artist.setAppearance(result.get("appearance") != null ? (int)result.get("appearance") : 0);
			artist.setTeam(result.get("team") != null ? (String)result.get("team") : "");
			list.add(artist);
		}
		
		return list;
	}

}
