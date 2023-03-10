package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.RegisterUserForm;
import com.example.demo.service.RegisterUserService;

/**
 * ユーザ情報を操作するコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/registerUser")
public class RegisterUserController {

	@Autowired
	private RegisterUserService registerUserService;

	/**
	 * ユーザ登録画面に遷移します.
	 * 
	 * @param form 入力情報を受け取るフォーム
	 * @return ユーザ登録画面
	 */
	@GetMapping("")
	public String index(RegisterUserForm form) {
		return "register";
	}

	/**
	 * ユーザ登録を行い、ログイン画面に遷移します.
	 * 
	 * @param form 入力情報を受け取るフォーム
	 * @return ログイン画面
	 */
	@PostMapping("/insert")
	public String insert(@Validated RegisterUserForm form, BindingResult result) {
		// 2つのパスワードが不一致だった場合にエラーを追加する
		if (!form.getPassword().equals(form.getConfimationPassword())) {
			FieldError fieldError = new FieldError(result.getObjectName(), "confimationPassword",
					"error:password does not match");
			result.addError(fieldError);
		}

		if (result.hasErrors()) {
			return "register";
		}

		registerUserService.insertUser(form);

		return "redirect:/login";
	}

}
