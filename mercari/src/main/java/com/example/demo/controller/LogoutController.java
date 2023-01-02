package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログアウトをするコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

	/**
	 * ログアウトをし、ログイン画面に遷移します.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("")
	public String logout() {
		return "/index?error=false";
	}

}
