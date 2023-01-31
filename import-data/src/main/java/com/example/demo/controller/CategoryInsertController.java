package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CategoryInsertService;

/**
 * カテゴリ情報を操作するコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryInsertController {

	@Autowired
	private CategoryInsertService categoryInsertService;

	/**
	 * カテゴリを挿入します.
	 * 
	 * @return 終了画面
	 */
	@GetMapping("/insert2")
	public String insert2() {
		categoryInsertService.insertCategories();
		
		return "finished";
	}

}
