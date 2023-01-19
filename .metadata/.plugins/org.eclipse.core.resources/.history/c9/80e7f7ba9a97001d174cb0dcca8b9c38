package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CategoryInsertService;

@Controller
@RequestMapping("/category")
public class CategoryInsertController {

	@Autowired
	private CategoryInsertService categoryInsertService;

	@GetMapping("/insert")
	public String insert() {
		categoryInsertService.findByCategoryName();

		return "sample";
	}

}
