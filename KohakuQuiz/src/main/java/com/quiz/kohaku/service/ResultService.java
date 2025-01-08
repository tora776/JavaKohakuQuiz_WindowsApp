package com.quiz.kohaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.kohaku.model.Result;
import com.quiz.kohaku.repository.IResultDao;

@Service
@Transactional
public class ResultService {
	private final IResultDao dao;
	
	@Autowired
	public ResultService(IResultDao dao) {
		this.dao = dao;
	}
	
	public List<Result> resultList(){
		return dao.resultList();
	}
}
