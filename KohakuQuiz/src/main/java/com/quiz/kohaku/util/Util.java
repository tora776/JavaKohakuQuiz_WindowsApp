package com.quiz.kohaku.util;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.quiz.kohaku.model.Artist;
import com.quiz.kohaku.model.Host;
import com.quiz.kohaku.model.Quiz;
import com.quiz.kohaku.model.Result;

import com.quiz.kohaku.service.ArtistService;
import com.quiz.kohaku.service.HostService;
import com.quiz.kohaku.service.QuizService;
import com.quiz.kohaku.service.ResultService;

@Component
public class Util {
	
	private final ArtistService artistService;

	private final HostService hostService;
	private final ResultService resultService;
	private final QuizService quizService;
	    
    public Util(ArtistService artistService, HostService hostService, ResultService resultService, QuizService quizService) {
        this.artistService = artistService;
        this.hostService = hostService;
        this.resultService = resultService;
        this.quizService = quizService;
    }
    
	
	public Quiz GenerateQuizzes() {
		// DBよりリストを取得
		List<Artist> artists = artistService.artistList();
		List<Host> hosts = hostService.hostList();
		List<Result> results = resultService.resultList();
		List<Quiz> quizzes = quizService.quizList();
		// ランダムな数値を取得
		Random random = new Random();
		int randomArtistName = random.nextInt(artists.size());
		
		Random random2 = new Random();
		int randomQuizNumber = random2.nextInt(quizzes.size());
		// クイズを作成
		// Quiz quiz = new Quiz();
		// quiz.setQuiz(artists.get(randomArtistName).getArtist_name() + "は2024年に出場している？");
		Quiz quiz = new Quiz();
		String quiz_template = quizzes.get(randomQuizNumber).getQuiz_template();
		quiz.setQuiz(quiz_template);
		return quiz;
	}
}
