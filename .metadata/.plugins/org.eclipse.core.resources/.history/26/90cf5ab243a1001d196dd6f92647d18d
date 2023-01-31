package com.example.demo.controller;

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

import com.example.demo.domain.Item;
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
		List<String> bigList = selectCategoryService.findBigAll();
		model.addAttribute("bigList", bigList);

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
		// コンディションの値が不正な場合にエラーを追加（1~5以外の場合）
		int conditionId=0;
		if(form.getConditionId()!=null) {
			conditionId=Integer.parseInt(form.getConditionId());
		}
		if (!(1<=conditionId && conditionId<=5)) {
			FieldError fieldError = new FieldError(result.getObjectName(), "conditionId", "error:may not be empty");
			result.addError(fieldError);
		}
		// categoryの値が不正な場合にエラーを追加（孫カテゴリまで選択されていない場合）
		if (form.getSmallName()==null ||form.getSmallName().equals("0")) {
			FieldError fieldError = new FieldError(result.getObjectName(), "smallName", "error:may not be empty");
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
