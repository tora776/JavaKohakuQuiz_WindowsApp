package com.quiz.kohaku.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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
		List<Artist> artistChoices = new ArrayList<>(3);
		// 現在日時を取得
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy"); 
		String yyyy = dtf1.format(nowDate);
		// DBよりリストを取得
		List<Artist> artists = artistService.artistList();
		List<Host> hosts = hostService.hostList();
		List<Result> results = resultService.resultList();
		// クイズを取得
		Quiz quiz = quizzes.get(quiz_id);
		// クイズのテンプレートを取得
		String quiz_template = quiz.getQuiz_template();
		
		
		// テンプレートに値を代入
		switch(quiz_id + 1) {
		
			case 1: // {yyyy}年現在、{アーティスト名}は過去{x}回紅白に出場している。
				// アーティスト名・出場回数を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template = quiz_template.replace("{x}", appearance);
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				/*
				// 選択肢を作成
				artistChoices = getArtistChoices(artist, artists);
				quiz = getArtistAppearanceChoices(quiz, artistChoices);
				*/
				
				
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
				/*
				// 選択肢を作成
				artistChoices = getArtistChoices(artist, artists);
				quiz = getArtistSongChoices(quiz, artistChoices);
				*/
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
				/*
				// 選択肢を作成
				artistChoices = getArtistChoices(artist, artists);
				quiz = getArtistSongChoices(quiz, artistChoices);
				*/
				break;
				
			case 5: // {アーティスト名}は{曲名}を{x}回歌っている？
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				artist_song = artist.getArtist_song();
				appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template = quiz_template.replace("{曲名}", artist_song); // 出力されず
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
				quiz_template = quiz_template.replace("{yyyy}年", "");
				// クイズを作成
				quiz.setQuiz(quiz_template);
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
	
	// 正誤含めた4件アーティストの選択肢を作成する
	private List<Artist> getArtistChoices(Artist artist, List<Artist> artists) {
		// 空のリストを作成
		List<Artist> artistChoices = new ArrayList<Artist>(3);
		// 正解を格納
		artistChoices.add(artist);
		// ダミーを格納
		for(int i = 0; i < artistChoices.size(); i++) {
			Artist dummyArtist = getRandomArtist(artists);
			artistChoices.add(dummyArtist);
		}
		// リストをシャッフルする
		Collections.shuffle(artistChoices);
				
		return artistChoices;
	}
	
	// 正誤含めた4件アーティストの選択肢を作成する
	private Quiz getArtistSongChoices(Quiz quiz, List<Artist> artistChoices) {
		// 選択肢にアーティストを格納する
		quiz.setChoice_template1(artistChoices.get(0).getArtist_song());
		quiz.setChoice_template2(artistChoices.get(1).getArtist_song());
		quiz.setChoice_template3(artistChoices.get(2).getArtist_song());
		quiz.setChoice_template4(artistChoices.get(3).getArtist_song());
		
		return quiz;
	}
	
	// 正誤含めた4件アーティストの選択肢を作成する
	private Quiz getArtistAppearanceChoices(Quiz quiz, List<Artist> artistChoices) {
		// 選択肢にアーティストを格納する
		quiz.setChoice_template1(String.valueOf(artistChoices.get(0).getAppearance()));
		quiz.setChoice_template1(String.valueOf(artistChoices.get(1).getAppearance()));
		quiz.setChoice_template1(String.valueOf(artistChoices.get(2).getAppearance()));
		quiz.setChoice_template1(String.valueOf(artistChoices.get(3).getAppearance()));
		
		return quiz;
	}
}
