package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Big;
import com.example.demo.domain.Middle;
import com.example.demo.domain.Small;
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
	@PostMapping("/middle")
	@ResponseBody
	public List<Middle> setMiddle(@RequestBody Big big) {
		List<Middle> middleList = selectCategoryService.findMiddleByParent(big.getId());
		return middleList;
	}

	/**
	 * 親カテゴリに紐づく子カテゴリを検索します.
	 * 
	 * @param middle 親カテゴリーのid
	 * @return 親カテゴリーに紐づく子カテゴリのリスト
	 */
	@PostMapping("/small")
	@ResponseBody
	public List<Small> setSmall(@RequestBody Middle middle) {
		List<Small> smallList = selectCategoryService.findSmallByParent(middle.getId());
		return smallList;
	}

}
