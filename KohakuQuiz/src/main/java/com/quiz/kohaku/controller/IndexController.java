package com.quiz.kohaku.controller;


import com.quiz.kohaku.model.Quiz;
import com.quiz.kohaku.util.Util;

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
	/*
	private final ArtistService artistService;
	private final HostService hostService;
	private final ResultService resultService;
	    
    @Autowired
    public IndexController(ArtistService artistService, HostService hostService, ResultService resultService) {
        this.artistService = artistService;
        this.hostService = hostService;
        this.resultService = resultService;
    }
    */
	
	// SpringのコンテナからUtilインスタンスを注入する
	@Autowired
	private Util util;
    

    @GetMapping("/")
    private String showForm(Model model) {
        // 10個のクイズを作成
        List<Quiz> quizList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
        	/*
            Quiz quiz = new Quiz();
            quiz.setQuiz("年の紅白歌合戦の総合司会は誰？");
            quizList.add(quiz);
            */
        	// クイズを作成
        	Quiz quiz = util.GenerateQuizzes();
        	quizList.add(quiz);
            // フォームをモデルに追加
	        model.addAttribute("form", new Form()); 
        }

        // クイズリストをモデルに追加
        model.addAttribute("quizList", quizList);

        return "index";
    }
		
		
	
	@PostMapping("/submit")
	public String submitForm(@ModelAttribute Form form, Model model) {
		model.addAttribute("answers", form.getAnswers());
		return "result";
	}
	/*
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
	*/
	
}