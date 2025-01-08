package com.quiz.kohaku.controller;


import com.quiz.kohaku.model.Artist;
import com.quiz.kohaku.model.Host;
import com.quiz.kohaku.model.Quiz;
import com.quiz.kohaku.model.Result;
import com.quiz.kohaku.service.ArtistService;
import com.quiz.kohaku.service.HostService;
import com.quiz.kohaku.service.ResultService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
	
	private final ArtistService artistService;
	private final HostService hostService;
	private final ResultService resultService;
	    
	    @Autowired
	    public IndexController(ArtistService artistService, HostService hostService, ResultService resultService) {
	        this.artistService = artistService;
	        this.hostService = hostService;
	        this.resultService = resultService;
	    }
	    
	
	    @GetMapping("/")
	    private String showForm(Model model) {
	        // 10個のクイズを作成
	        List<Quiz> quizList = new ArrayList<>();
	        for (int i = 0; i < 10; i++) {
	            Quiz quiz = new Quiz();
	            quiz.setQuiz("2024年紅白歌合戦の総合司会は誰？（問題 " + (i + 1) + "）");
	            quizList.add(quiz);
	        }

	        // フォームに回答リストを追加
	        Form form = new Form();
	        form.setAnswers(new ArrayList<>(quizList.size())); // 初期リストを作成
	        for (int i = 0; i < quizList.size(); i++) {
	            form.getAnswers().add(""); // 空の回答を追加
	        }
	        model.addAttribute("form", form);

	        // クイズリストをモデルに追加
	        model.addAttribute("quizList", quizList);

	        return "index";
	    }
		
		
	
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute Form form, Model model) {
        model.addAttribute("answers", form.getAnswers()); // 複数の回答をモデルに追加
        return "result";
    }
	
	@GetMapping("/artists")
	public String showArtists(Model model) {
	    // アーティストデータを取得してモデルに追加
	    List<Artist> artists = artistService.artistList();
	    model.addAttribute("artists", artists);

	    // artist.htmlを表示
	    return "artist";
	}
	
	@GetMapping("/hosts")
	public String showHosts(Model model) {
	    // ホストデータを取得してモデルに追加
	    List<Host> hosts = hostService.hostList();
	    model.addAttribute("hosts", hosts);

	    // host.htmlを表示
	    return "host";
	}
	
	@GetMapping("/resultKohaku")
	public String showResults(Model model) {
	    // 勝敗データを取得してモデルに追加
	    List<Result> results = resultService.resultList();
	    model.addAttribute("results", results);

	    // result.htmlを表示
	    return "resultKohaku";
	}
	
}