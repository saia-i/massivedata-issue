package com.example.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ItemInsertService;

@Controller
@RequestMapping("/itemInsert")
public class ItemInsertController {

	@Autowired
	private ItemInsertService itemInsertService;


	@GetMapping("/allData")
	public String insertAllData() throws SQLException {
		itemInsertService.insertAllData();
		return "redirect:sample";
	}

}
