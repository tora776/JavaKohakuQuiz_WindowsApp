package com.quiz.kohaku.util;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.quiz.kohaku.model.Artist;
import com.quiz.kohaku.model.Quiz;
import com.quiz.kohaku.service.ArtistService;
import com.quiz.kohaku.service.HostService;
import com.quiz.kohaku.service.ResultService;

@Component
public class Util {
	
	private final ArtistService artistService;
	// private final HostService hostService;
	// private final ResultService resultService;
	    
    public Util(ArtistService artistService, HostService hostService, ResultService resultService) {
        this.artistService = artistService;
        // this.hostService = hostService;
        // this.resultService = resultService;
    }
    
	
	public Quiz GenerateQuizzes() {
		// DBよりartist型のリストを取得
		List<Artist> artists = artistService.artistList();
		// ランダムな数値を取得
		Random random = new Random();
		int randomArtistName = random.nextInt(artists.size());
		// クイズを作成
		Quiz quiz = new Quiz();
		quiz.setQuiz(artists.get(randomArtistName).getArtist_name() + "は2024年に出場している？");
	
		return quiz;
	}
}
