package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Big;
import com.example.demo.domain.Item;
import com.example.demo.domain.Middle;
import com.example.demo.domain.Small;
import com.example.demo.form.InsertItemForm;
import com.example.demo.service.ItemInsertService;
import com.example.demo.service.SelectCategoryService;
import com.example.demo.service.ShowDetailService;

/**
 * 商品情報を操作するコントローラ.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/itemInsert")
public class ItemInsertController {

	@Autowired
	private ItemInsertService itemInsertService;

	@Autowired
	private ShowDetailService showDetailService;

	@Autowired
	private SelectCategoryService selectCategoryService;

	/**
	 * 商品追加画面の表.
	 * 
	 * @param updateItemForm 入力情報を受け取るフォーム
	 * @return 商品追加画面
	 */
	@GetMapping("/")
	public String index(InsertItemForm insertItemForm, Model model) {
		List<Big> bigList = selectCategoryService.findBigAll();
		model.addAttribute("bigList", bigList);

		// middleの初期表示をするための記述
		List<Middle> middleList = new ArrayList<>();
		Middle middle = new Middle();
		middle.setId(0);
		middle.setName("---");
		middleList.add(middle);
		model.addAttribute("middleList", middleList);

		// smallの初期表示をするための記述
		List<Small> smallList = new ArrayList<>();
		Small small = new Small();
		small.setId(0);
		small.setName("---");
		smallList.add(small);
		model.addAttribute("smallList", smallList);

		return "add";
	}

	/**
	 * 商品情報を追加し、商品詳細画面に遷移する.
	 * 
	 * @param form 入力情報を受け取るフォーム.
	 * @return 商品詳細画面
	 */
	@PostMapping("/add")
	public String add(@Validated InsertItemForm form, BindingResult result, Model model) {
		// priceの値が不正な場合にエラーを追加（0.3から9999999.9以外の値の場合）
		double price = 0;
		if (!form.getPrice().equals("")) {
			price = Double.parseDouble(form.getPrice());
		}
		if (!(0.3 <= price && price <= 9999999.9)) {
			FieldError fieldError = new FieldError(result.getObjectName(), "price",
					"error:must be between 0.3 and 9999999.9");
			result.addError(fieldError);
		}
		// コンディションの値が不正な場合にエラーを追加（1,2,3以外の場合）
		if (form.getConditionId() == null || !(form.getConditionId().equals("1") || form.getConditionId().equals("2")
				|| form.getConditionId().equals("3"))) {
			FieldError fieldError = new FieldError(result.getObjectName(), "conditionId", "error:may not be empty");
			result.addError(fieldError);
		}
		// categoryの値が不正な場合にエラーを追加（孫カテゴリまで選択されていない場合）
		if (form.getCategory().equals("0") || form.getCategory() == null) {
			FieldError fieldError = new FieldError(result.getObjectName(), "category", "error:may not be empty");
			result.addError(fieldError);
		}
		// エラーが存在していた場合に商品追加画面に戻る
		if (result.hasErrors()) {
			return index(form, model);
		}
		// 挿入された商品に採番されたidを取得し、商品詳細を検索する
		int id = itemInsertService.insertItem(form);
		Item item = showDetailService.showDetail(id);
		model.addAttribute("item", item);

		return "redirect:/showDetail?id=" + id;
	}

}
