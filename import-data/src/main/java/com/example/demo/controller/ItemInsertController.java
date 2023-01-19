package com.example.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ItemInsertService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/itemInsert")
public class ItemInsertController {

	@Autowired
	private ItemInsertService itemInsertService;

	/**
	 * オリジナルテーブルの全データを商品テーブルに挿入します.
	 * 
	 * @return 完了画面にリダイレクト
	 * @throws SQLException
	 */
	@GetMapping("/allData")
	public String insertAllData() throws SQLException {
		itemInsertService.insertAllData();
		return "redirect:finished";
	}

}
