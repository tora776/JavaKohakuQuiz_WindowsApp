package com.quiz.kohaku.controller;


import com.quiz.kohaku.model.Quiz;
import com.quiz.kohaku.util.GenerateQuiz;

import jakarta.servlet.http.HttpSession;

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
	private GenerateQuiz generateQuiz;
    

    @GetMapping("/")
    private String showForm(Model model, HttpSession session) {
        // 10個のクイズを作成
        List<Quiz> quizList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
        	// クイズを作成
        	Quiz quiz = generateQuiz.GenerateQuizzes();
        	quizList.add(quiz);
        }
        Form form = new Form();
        // セッションにクイズリストを保存
        session.setAttribute("quizList", quizList);
        
        // クイズリストをモデルに追加
        model.addAttribute("quizList", quizList);
        model.addAttribute("form", form);

        return "index";
    }
		
	


	@PostMapping("/submit")
	public String submitForm(@ModelAttribute Form form, Model model, HttpSession session) {
		// フォームから受け取った回答リストをモデルに追加
	    List<String> answers = form.getAnswers();
	    List<Boolean> corrections = form.getCorrections();
	    // セッションからクイズリストを取得
	    @SuppressWarnings("unchecked")
        List<Quiz> quizList = (List<Quiz>) session.getAttribute("quizList");
	    
	    if(answers == null) {
	    	answers = new ArrayList<>();
	    }
	    
	    if(corrections == null) {
	    	corrections = new ArrayList<>();
	    }
	    
	    // nullの項目に未回答と記載する
	    for (int i = 0; i < answers.size(); i++) {
	        if (answers.get(i) == null) {
	            answers.set(i, "未回答");
	        }
	    }
	    // answersリストが10件ないとエラーが発生する
	    while (answers.size() < 10) {
	    	answers.add("未回答");
	    }
	    
	    // 正誤チェック
	    for (int i = 0; i < answers.size(); i++) {
	    	boolean bool = true;
	    	if (!answers.get(i).equals(quizList.get(i).getCorrectAnswer())) {
	    		bool = false;
	    	} 
	    	corrections.add(bool);
	    }
	    
	    model.addAttribute("answers", answers);
	    model.addAttribute("corrections", corrections);
	    model.addAttribute("quizList", quizList);

	    return "result";
	}
	
}