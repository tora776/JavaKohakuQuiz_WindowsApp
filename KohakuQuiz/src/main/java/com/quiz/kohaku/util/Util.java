package com.quiz.kohaku.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		// String quiz_template = quizzes.get(quiz_id).getQuiz_template();
		// quiz.setQuiz(quiz_template);
		quiz = buildQuizFromTemplate(quiz_id, quizzes);
		// 選択肢を作成
		
		return quiz;
	}
	
	private Quiz buildQuizFromTemplate(int quiz_id, List<Quiz> quizzes) {
		// 初期化
		Artist artist;
		Host host;
		Result result;
		String artist_name;
		String appearance;
		Artist artist2;
		String artist2_name;
		String artist_song;
		String year;
		// 現在日時を取得
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy"); // ①
		String yyyy = dtf1.format(nowDate);
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
		
			case 1: // {yyyy}年現在、{アーティスト名}は過去{x}回紅白に出場している。
				// アーティスト名・出場回数を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template = quiz_template.replace("{x}", appearance);
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 2: // {yyyy}年現在、{アーティスト名_1}は{アーティスト名_2}より紅白の出場回数が多い。
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				artist2 = getRandomArtist(artists);
				artist2_name = artist2.getArtist_name();
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名_1}", artist_name);
				quiz_template = quiz_template.replace("{アーティスト名_2}", artist2_name);
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 3: // {アーティスト名}が紅白で初めて歌った楽曲は何？
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 4: // {アーティスト名}が紅白出場{x}回目で歌った楽曲は何？
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template = quiz_template.replace("{x}", appearance);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 5: // {アーティスト名}は{曲名}を{x}回歌っている？
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				artist_song = artist.getArtist_song();
				appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template = quiz_template.replace("{曲名}", artist_song);
				quiz_template = quiz_template.replace("{x}", appearance);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 6: // {yyyy}年の紅白歌合戦の総合司会は誰？
				host = getRandomHost(hosts);
				year = String.valueOf(host.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 7: // {yyyy}年の紅白歌合戦の紅組司会は誰？
				host = getRandomHost(hosts);
				year = String.valueOf(host.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 8: // {yyyy}年の紅白歌合戦の白組司会は誰？
				host = getRandomHost(hosts);
				year = String.valueOf(host.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 9: // {yyyy}年の紅白歌合戦で司会を務めたのは、a,bとあと一人は？
				host = getRandomHost(hosts);
				year = String.valueOf(host.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
				
			case 10: // {yyyy}年現在、歴代紅白歌合戦でどちらのほうが勝利回数が多い？
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				break;
			case 11: // 昨年{yyyy}年の紅白歌合戦の勝利チームは？
				result = getRandomResult(results);
				year = String.valueOf(result.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				// クイズを作成
				quiz.setQuiz(quiz_template);
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
	
	private Artist getRandomArtist(List<Artist> artists) {
		Random random = new Random();
		int artist_id = random.nextInt(artists.size());
		Artist artist = artists.get(artist_id);
		return artist;
	}
	
	private Host getRandomHost(List<Host> hosts) {
		Random random = new Random();
		int host_id = random.nextInt(hosts.size());
		Host host = hosts.get(host_id);
		return host;
	}
	
	private Result getRandomResult(List<Result> results) {
		Random random = new Random();
		int result_id = random.nextInt(results.size());
		Result result = results.get(result_id);
		return result;
	}
}
