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
		List<Quiz> quizzes = quizService.quizList();
		// ランダムな数値を取得
		Random random = new Random();
		int quiz_id = random.nextInt(quizzes.size());
		// クイズを作成
		Quiz quiz = new Quiz();
		String quiz_template = quizzes.get(quiz_id).getQuiz_template();
		quiz.setQuiz(quiz_template);
		// 選択肢を作成
		
		return quiz;
	}
	
	private Quiz buildQuizFromTemplate(int quiz_id, List<Quiz> quizzes) {
		// DBよりリストを取得
		List<Artist> artists = artistService.artistList();
		List<Host> hosts = hostService.hostList();
		List<Result> results = resultService.resultList();
		// クイズを取得
		Quiz quiz = quizzes.get(quiz_id);
		// クイズのテンプレートを取得
		String quiz_template = quiz.getQuiz_template();
		String choice_template1 = quiz.getChoice_template1();
		String choice_template2 = quiz.getChoice_template2();
		String choice_template3 = quiz.getChoice_template3();
		String choice_template4 = quiz.getChoice_template4();
		
		// テンプレートに値を代入
		switch(quiz_id) {
			case 1: // 2025年現在、{アーティスト名}は過去{x}回紅白に出場している。
				// アーティスト名・出場回数を取得
				Random random = new Random();
				int artist_id = random.nextInt(artists.size());
				Artist artist = artists.get(artist_id);
				String artist_name = artist.getArtist_name();
				String appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template.replace("{x}", appearance);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
			case 2: // 2025年現在、{アーティスト名}は{アーティスト名}より紅白の出場回数が多い。
				
				break;
			case 3: // {アーティスト名}が紅白で初めて歌った楽曲は何？
				
				break;
			case 4: // {アーティスト名}が紅白出場{x}回目で歌った楽曲は何？
				
				break;
			case 5: // {アーティスト名}は{曲名}を{x}回歌っている？
				
				break;
			case 6: // {yyyy}年の紅白歌合戦の総合司会は誰？
				
				break;
				
			case 7: // {yyyy}年の紅白歌合戦の紅組司会は誰？
				
				break;
			case 8: // {yyyy}年の紅白歌合戦の白組司会は誰？
				
				break;
			case 9: // {yyyy}年の紅白歌合戦で司会を務めたのは、a,bとあと一人は？
				
				break;
			case 10: // 2025年現在、歴代紅白歌合戦でどちらのほうが勝利回数が多い？
				
				break;
			case 11: // 昨年{yyyy年}の紅白歌合戦の勝利チームは？
				
				break;
			default:
		}
		return quiz;
	}
	
	private Quiz buildChoiceFromTemplate(int quiz_id, List<Quiz> quizzes) {
		// DBよりリストを取得
		List<Artist> artists = artistService.artistList();
		List<Host> hosts = hostService.hostList();
		List<Result> results = resultService.resultList();
		// クイズを取得
		Quiz quiz = quizzes.get(quiz_id);
		// テンプレートに値を代入
			switch(quiz_id) {
				case 1: // 2025年現在、{アーティスト名}は過去{x}回紅白に出場している。
					// アーティスト名・出場回数を取得
					Random random = new Random();
					int artist_id = random.nextInt(artists.size());
					Artist artist = artists.get(artist_id);
					String artist_name = artist.getArtist_name();
					String appearance = String.valueOf(artist.getAppearance());
					// 選択肢を置き換え
					
					// クイズを作成
					
					break;
				case 2: // 2025年現在、{アーティスト名}は{アーティスト名}より紅白の出場回数が多い。
					
					break;
				case 3: // {アーティスト名}が紅白で初めて歌った楽曲は何？
					
					break;
				case 4: // {アーティスト名}が紅白出場{x}回目で歌った楽曲は何？
					
					break;
				case 5: // {アーティスト名}は{曲名}を{x}回歌っている？
					
					break;
				case 6: // {yyyy}年の紅白歌合戦の総合司会は誰？
					
					break;
					
				case 7: // {yyyy}年の紅白歌合戦の紅組司会は誰？
					
					break;
				case 8: // {yyyy}年の紅白歌合戦の白組司会は誰？
					
					break;
				case 9: // {yyyy}年の紅白歌合戦で司会を務めたのは、a,bとあと一人は？
					
					break;
				case 10: // 2025年現在、歴代紅白歌合戦でどちらのほうが勝利回数が多い？
					
					break;
				case 11: // 昨年{yyyy年}の紅白歌合戦の勝利チームは？
					
					break;
				default:
			}
		
		return quiz;
	}
}
