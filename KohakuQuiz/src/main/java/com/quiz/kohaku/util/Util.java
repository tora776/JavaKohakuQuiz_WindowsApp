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
		String correctAnswer;
		List<Artist> artistChoices = new ArrayList<>(3);
		List<Host> hostChoices = new ArrayList<>(3);
		
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
		
			case 1: // {yyyy}年現在、{アーティスト名}は過去何回紅白に出場している？
				// アーティスト名・出場回数を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template = quiz_template.replace("{x}", appearance);
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				
				// 選択肢を作成
				List<String> appearances = getRandomIntChoices(appearance);
				quiz = getArtistAppearanceChoices(quiz, appearances);

				// 作成したクイズを格納
				quiz.setQuiz(quiz_template);
				// 正解を格納
				quiz.setCorrectAnswer(appearance);
				break;
				
			case 2: // {yyyy}年現在、{アーティスト名_1}は{アーティスト名_2}より紅白の出場回数が多い？
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				artist2 = getRandomArtist(artists);
				artist2_name = artist2.getArtist_name();
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名_1}", artist_name);
				quiz_template = quiz_template.replace("{アーティスト名_2}", artist2_name);
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				// 作成したクイズを格納
				quiz.setQuiz(quiz_template);
				// 正解を格納
				if (artist.getAppearance() > artist2.getAppearance()) {
					quiz.setCorrectAnswer("多い");
				} else if (artist.getAppearance() < artist2.getAppearance()) {
					quiz.setCorrectAnswer("少ない");
				} else {
					quiz.setCorrectAnswer("同じ");
				}
				break;
				
			case 3: // {アーティスト名}が紅白で初めて歌った楽曲は何？
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				// 正解をDBから取得。case3のため、getCorrectAnswerの引数に3を使用。
				correctAnswer = quizService.getArtistCorrectAnswer(3, artist_name);
				if(correctAnswer != "") {
					quiz.setCorrectAnswer(correctAnswer);
					// 正解をartist_songに格納
					artist.setArtist_song(correctAnswer);
				} else {
					quiz.setCorrectAnswer(correctAnswer);
					// 正解をartist_songに格納
					artist.setArtist_song("答えなし");
				}
				
				// 選択肢を作成
				artistChoices = getArtistChoices(artist, artists);
				quiz = getArtistSongChoices(quiz, artistChoices);
				
				
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
				// 選択肢を作成
				artistChoices = getArtistChoices(artist, artists);
				quiz = getArtistSongChoices(quiz, artistChoices);
				quiz.setCorrectAnswer(artist.getArtist_song());
				break;
				
			case 5: // {アーティスト名}は{曲名}を何回歌っている？
				// アーティスト名を取得
				artist = getRandomArtist(artists);
				artist_name = artist.getArtist_name();
				artist_song = artist.getArtist_song();
				appearance = String.valueOf(artist.getAppearance());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{アーティスト名}", artist_name);
				quiz_template = quiz_template.replace("{曲名}", artist_song); 
				// クイズを作成
				quiz.setQuiz(quiz_template);
				// 正解をDBから取得。case5のため、getCorrectAnswerの引数に5を使用。
				correctAnswer = quizService.getArtistCorrectAnswer(5, artist_name, artist_song);
				// 選択肢を作成
				List<String> countSong = getRandomIntChoices(correctAnswer);
				quiz = getArtistAppearanceChoices(quiz, countSong);
				
				break;
				
			case 6: // {yyyy}年の紅白歌合戦の総合司会は誰？
				int host_year = getRandomYear(hosts);
				year = String.valueOf(host_year);
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				// 正解をDBから取得。case6のため、getCorrectAnswerの引数に6を使用。
				List<Host> correctHosts = quizService.getHostCorrectAnswer(6, year, "総合司会");
				// correctHostsがnullの場合
				if(correctHosts.size() == 0) {
					Host nullHost = new Host();
					nullHost.setHost_name("該当の司会なし");
					correctHosts.add(nullHost);
				} 
				// 正解を格納
				hostChoices.add(correctHosts.get(0));
				
				// 選択肢を作成
				hostChoices = getHostChoices(correctHosts, hosts);
				quiz = getHostNameChoices(quiz, hostChoices);
				quiz.setCorrectAnswer(correctHosts.get(0).getHost_name());
				break;
				
			case 7: // {yyyy}年の紅白歌合戦の紅組司会(女性司会)は誰？
				host = getRandomHost(hosts);
				year = String.valueOf(host.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				// 正解をDBから取得。case6のため、getCorrectAnswerの引数に7を使用。
				correctHosts = quizService.getHostCorrectAnswer(7, year, "紅組司会", "女性司会");
				// 選択肢を作成
				hostChoices = getHostChoices(correctHosts, hosts);
				quiz = getHostNameChoices(quiz, hostChoices);
				quiz.setCorrectAnswer(correctHosts.get(0).getHost_name());
				break;
				
			case 8: // {yyyy}年の紅白歌合戦の白組司会は誰？
				host = getRandomHost(hosts);
				year = String.valueOf(host.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				// 正解をDBから取得。case6のため、getCorrectAnswerの引数に8を使用。
				correctHosts = quizService.getHostCorrectAnswer(8, year, "白組司会", "男性司会");
				// 選択肢を作成
				hostChoices = getHostChoices(correctHosts, hosts);
				quiz = getHostNameChoices(quiz, hostChoices);
				quiz.setCorrectAnswer(correctHosts.get(0).getHost_name());
				break;
				
			case 9: // {yyyy}年の紅白歌合戦で司会を務めたのは、次のうち誰？
				host = getRandomHost(hosts);
				year = String.valueOf(host.getYear());
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", year);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				// 正解をDBから取得。case6のため、getCorrectAnswerの引数に9を使用。
				correctHosts = quizService.getHostCorrectAnswer(9, year);
				// 選択肢を作成
				hostChoices = getHostChoices(correctHosts, hosts);
				quiz = getHostNameChoices(quiz, hostChoices);
				quiz.setCorrectAnswer(correctHosts.get(0).getHost_name());
				break;
				
			case 10: // {yyyy}年現在、歴代紅白歌合戦でどちらのほうが勝利回数が多い？
				// テンプレートを置き換え
				quiz_template = quiz_template.replace("{yyyy}", yyyy);
				// クイズを作成
				quiz.setQuiz(quiz_template);
				quiz.setCorrectAnswer(getWinnerTeamHistory(results));
				break;
			case 11: // 昨年の紅白歌合戦の勝利チームは？
				result = results.get(0);
				year = String.valueOf(result.getYear());
				// クイズを作成
				quiz.setQuiz(quiz_template);
				quiz.setCorrectAnswer(result.getWinner());
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
	
	private int getRandomYear(List<Host> hosts) {
		Random random = new Random();
		int host_id = random.nextInt(hosts.size());
		Host host = hosts.get(host_id);
	
		return host.getYear();
	}
	
	
	private Host getRandomHost(List<Host> hosts) {
		Random random = new Random();
		int host_id = random.nextInt(hosts.size());
		Host host = hosts.get(host_id);
	
		return host;
	}
	
	
	private List<String> getRandomIntChoices(String appearance){
		// 空のリストを作成
		List<String> appearances = new ArrayList<>(3);
		// 正解を格納
		appearances.add(appearance);
		// ダミーを格納
		Random random = new Random();
		while(appearances.size() < 4) {
			// 正しい出場回数が+-5回となるように設定。appearanceが負の数の場合は追加しない。
			int dummyAppearance = Integer.valueOf(appearance) + random.nextInt(11) - 5;
			// 重複チェックのためのフラグ
	        boolean isDuplicate = false;
	        for (String existingAppearance : appearances) {
	            if (existingAppearance.equals(String.valueOf(dummyAppearance))) {
	                isDuplicate = true; // 重複を検出
	                break; // ループを抜ける
	            }
	        }
	        // 重複がない場合
	        if (!isDuplicate) {
	        	// ダミーの値が正の数であればリストに追加
				if(dummyAppearance > 0) {
					appearances.add(String.valueOf(dummyAppearance));
				}
	        }			
		}
		// リストをシャッフルする
		Collections.shuffle(appearances);
		
		return appearances;
	}
	// 正誤含めた4件アーティストの選択肢を作成する
	private List<Artist> getArtistChoices(Artist artist, List<Artist> artists) {
		// 空のリストを作成
		List<Artist> artistChoices = new ArrayList<Artist>(3);
		// 正解を格納
		artistChoices.add(artist);
		// ダミーを格納
	    while (artistChoices.size() < 4) { // 4件になるまでループ
	        Artist dummyArtist = getRandomArtist(artists);
	        // 重複チェックのためのフラグ
	        boolean isDuplicate = false;
	        for (Artist existingArtist : artistChoices) {
	            if (existingArtist.getArtist_name().equals(dummyArtist.getArtist_name())) {
	                isDuplicate = true; // 重複を検出
	                break; // ループを抜ける
	            }
	        }
	        if (!isDuplicate) {
	            artistChoices.add(dummyArtist); // 重複がなければ追加
	        }
	    }
		// リストをシャッフルする
		Collections.shuffle(artistChoices);
				
		return artistChoices;
	}
	
	// 正誤含めた4件アーティストの選択肢を作成する
	private Quiz getArtistSongChoices(Quiz quiz, List<Artist> artistChoices) {
		// 選択肢にアーティストを格納する
		quiz.setAnswer1(artistChoices.get(0).getArtist_song());
		quiz.setAnswer2(artistChoices.get(1).getArtist_song());
		quiz.setAnswer3(artistChoices.get(2).getArtist_song());
		quiz.setAnswer4(artistChoices.get(3).getArtist_song());
		
		return quiz;
	}
	
	// 正誤含めた4件アーティストの選択肢を作成する
	private Quiz getArtistAppearanceChoices(Quiz quiz, List<String> appearances) {
		// 選択肢にアーティストを格納する
		quiz.setAnswer1(appearances.get(0));
		quiz.setAnswer2(appearances.get(1));
		quiz.setAnswer3(appearances.get(2));
		quiz.setAnswer4(appearances.get(3));
		
		return quiz;
	}
	
	// 正誤含めた4件司会者の選択肢を作成する
		private List<Host> getHostChoices(List<Host> correctHosts, List<Host> hosts) {
			// 空のリストを作成
			List<Host> hostChoices = new ArrayList<Host>(3);
			// 正解を格納
			hostChoices.add(correctHosts.get(0));
			// ダミーを格納
		    while (hostChoices.size() < 4) { // 4件になるまでループ
		        Host dummyHost = getRandomHost(hosts);
		        // 重複チェックのためのフラグ
		        boolean isDuplicate = false;
		        for (Host existingHost : hostChoices) {
		            if (existingHost.equals(dummyHost)) {
		                isDuplicate = true; // 重複を検出
		                break; // ループを抜ける
		            }
		        }
		        // 重複がない場合
		        if (!isDuplicate) {
		            hostChoices.add(dummyHost); // 重複がなければ追加
		        }
		    }
			// リストをシャッフルする
			Collections.shuffle(hostChoices);
				
			return hostChoices;
		}
		
		// 正誤含めた4件アーティストの選択肢を作成する
		private Quiz getHostNameChoices(Quiz quiz, List<Host> hostChoices) {
			// 選択肢にアーティストを格納する
			quiz.setAnswer1(hostChoices.get(0).getHost_name());
			quiz.setAnswer2(hostChoices.get(1).getHost_name());
			quiz.setAnswer3(hostChoices.get(2).getHost_name());
			quiz.setAnswer4(hostChoices.get(3).getHost_name());
			
			return quiz;
		}
		
		private String getWinnerTeamHistory(List<Result> results) {
			List<String> winners = new ArrayList<String>(results.size());
			// 紅白の勝敗のみを格納したリストwinnersを作成する
			for (int i = 0; i < results.size(); i++) {
				winners.add(results.get(i).getWinner());
			}
			// 初期化
			int countRedTeam = 0;
			int countWhiteTeam = 0;
			// 紅組と白組の個数を取得する
			for (String winner : winners) {
				if("紅組".equals(winner)) {
					countRedTeam++;
				} else if("白組".equals(winner)) {
					countWhiteTeam++;
				}
			}
			// 個数が多い方を返す
			if (countRedTeam > countWhiteTeam) {
				return "紅組";
			} else if (countRedTeam < countWhiteTeam) {
				return "白組";
			} else if (countRedTeam == countWhiteTeam) {
				return "同じ";
			}
			return null;

		}
}
