package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.BrandInsertService;

/**
 * ブランド情報を操作するコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/brandInsert")
public class BrandInsertController {

	@Autowired
	private BrandInsertService brandInsertService;

	/**
	 * ブランド情報を挿入します.
	 * 
	 * @return 完了画面
	 */
	@GetMapping("")
	public String insert() {
		brandInsertService.insert();
		return "finished";
	}

}
