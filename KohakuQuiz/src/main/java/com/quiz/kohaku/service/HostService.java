package com.quiz.kohaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.kohaku.model.Host;
import com.quiz.kohaku.repository.IHostDao;

@Service
@Transactional
public class HostService {
private final IHostDao dao;
	
	@Autowired
	public HostService(IHostDao dao) {
		this.dao = dao;
	}
	
	public List<Host> hostList(){
		return dao.hostList();
	}
}
