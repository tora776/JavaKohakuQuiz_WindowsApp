package com.quiz.kohaku.repository;

import java.util.List;

import com.quiz.kohaku.model.Host;

public interface IHostDao {
	// DBのホスト名を取得
		List<Host> hostList();
}
