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
	
	// SpringのコンテナからUtilインスタンスを注入する
	@Autowired
	private Util util;
    

    @GetMapping("/")
    private String showForm(Model model) {
        // 10個のクイズを作成
        List<Quiz> quizList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
        	// クイズを作成
        	Quiz quiz = util.GenerateQuizzes();
        	quizList.add(quiz);
            // フォームをモデルに追加
	        model.addAttribute("form", new Form()); 
	        System.out.println(quiz.getAnswer1());
        }

        // クイズリストをモデルに追加
        model.addAttribute("quizList", quizList);

        return "index";
    }
		
	
	@PostMapping("/submit")
	public String submitForm(@ModelAttribute Form form, Model model) {
		 // フォームから受け取った回答リストをモデルに追加
	    List<String> answers = form.getAnswers();
	    model.addAttribute("answers", answers);

	    return "result";
	}
	
}