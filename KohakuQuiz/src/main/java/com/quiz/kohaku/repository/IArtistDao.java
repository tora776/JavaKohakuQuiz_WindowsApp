package com.quiz.kohaku.repository;

import java.util.List;

import com.quiz.kohaku.model.*;

public interface IArtistDao {
	// DBのアーティスト名を取得
	List<Artist> artistList();
}
