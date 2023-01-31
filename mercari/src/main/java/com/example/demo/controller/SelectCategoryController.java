package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Category;
import com.example.demo.service.SelectCategoryService;

/**
 * カテゴリープルダウンの操作を行うコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/selectCategory")
public class SelectCategoryController {

	@Autowired
	private SelectCategoryService selectCategoryService;

	/**
	 * 親カテゴリに紐づく子カテゴリを検索します.
	 * 
	 * @param big 親カテゴリーのid
	 * @return 親カテゴリーに紐づく子カテゴリのリスト
	 */
	@PostMapping("/getChildList")
	@ResponseBody
	public List<Category> setMiddle(@RequestBody Category category) {
		List<Category> childList = selectCategoryService.getChildList(category.getPath(),category.getHierarchy());
		return childList;
	}


}
