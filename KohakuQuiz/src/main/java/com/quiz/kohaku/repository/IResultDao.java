package com.quiz.kohaku.repository;

import java.util.List;

import com.quiz.kohaku.model.Result;

public interface IResultDao {
	// DBのアーティスト名を取得
		List<Result> resultList();
}
