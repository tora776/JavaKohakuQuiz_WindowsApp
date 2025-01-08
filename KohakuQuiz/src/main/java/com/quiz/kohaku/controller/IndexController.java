package com.quiz.kohaku.controller;


import com.quiz.kohaku.model.Artist;
import com.quiz.kohaku.model.Quiz;
import com.quiz.kohaku.service.ArtistService;

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
	    
	    @Autowired
	    public IndexController(ArtistService artistService) {
	        this.artistService = artistService;
	    }
	
	@GetMapping("/")
	private String showForm(Model model) {
		model.addAttribute("form", new Form());
		
		Quiz quiz = new Quiz();
		quiz.setQuiz("2024年紅白歌合戦の総合司会は誰？");
		model.addAttribute("quiz", quiz);
		return "index";
	}
		
		
	
	@PostMapping("/submit")
	public String submitForm(@ModelAttribute Form form, Model model) {
		model.addAttribute("answer", form.getAnswer());
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
	
}
