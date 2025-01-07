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
		sqlBuilder.append("SELECT d.participation_id, d.artist_name, d.artist_song, d.year, d.appearance, d.team FROM artist");
		String sql = sqlBuilder.toString();
		
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		// タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		// return用の空のListを用意
		List<Artist> list = new ArrayList<Artist>();
		
		// データをDiaryにまとめる
		for(Map<String, Object>result : resultList) {
			Artist artist = new Artist();
			artist.setParticipation_id((int)result.get("participation_id"));
			artist.setArtist_name((String)result.get("artist_name"));
			artist.setArtist_song((String)result.get("artist_song"));
			artist.setYear((int)result.get("year"));
			artist.setAppearance((int)result.get("appearance"));
			artist.setTeam((String)result.get("team"));
			list.add(artist);
		}
		return list;
	}

}
