package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginUserForm;
import com.example.demo.service.LoginService;

/**
 * ログイン管理をするコントローラ.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private HttpSession session;

	/**
	 * ログイン画面を表示する.
	 * 
	 * @param error エラーの有無の判定
	 * @return ログイン画面
	 */
	@GetMapping("/index")
	public String index(LoginUserForm form, Boolean error, Model model) {
		if (error) {
			model.addAttribute("error", "error:failed to login");
		}
		return "login";
	}

	/**
	 * ログインをします.
	 * 
	 * @param form 入力情報を受け取るフォーム
	 * @return 商品一覧画面
	 */
	@PostMapping("/login")
	public String login(LoginUserForm form) {
		return "/";
	}

}