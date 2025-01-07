package com.quiz.kohaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.kohaku.model.Artist;
import com.quiz.kohaku.repository.IArtistDao;

@Service
@Transactional
public class ArtistService {
	private final IArtistDao dao;
	
	@Autowired
	public ArtistService(IArtistDao dao) {
		this.dao = dao;
	}
	
	public List<Artist> artistList(){
		return dao.artistList();
	}
	
}
