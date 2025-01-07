package com.quiz.kohaku.controller;

import com.quiz.kohaku.model.Quiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
	
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
	
}
