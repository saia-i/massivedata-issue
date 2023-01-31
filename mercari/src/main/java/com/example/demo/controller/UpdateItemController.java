package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.form.UpdateItemForm;
import com.example.demo.service.SelectCategoryService;
import com.example.demo.service.UpdateItemService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author inagakisaia
 *
 */
@Controller
@RequestMapping("/updateItem")
public class UpdateItemController {

	@Autowired
	private UpdateItemService updateItemService;

	@Autowired
	private SelectCategoryService selectCategoryService;

	/**
	 * 商品情報変更画面に遷移します.
	 * 
	 * @param itemId アイテムID
	 * @return 商品変更画面
	 */
	@GetMapping("/")
	public String showEditItem(UpdateItemForm form, int id, Model model) {
		Item item = updateItemService.showEditItem(id);
		// 入力フォームに表示させるためにformに情報をコピーする
		BeanUtils.copyProperties(item, form);
		form.setPrice(String.valueOf(item.getPrice()));
		form.setBigName(item.getCategoryDetail().getBigName());
		form.setMiddleName(item.getCategoryDetail().getMiddleName());
		form.setSmallName(item.getCategoryDetail().getSmallName());
		form.setBrand(item.getBrandName());
		form.setConditionId(item.getConditionId().toString());
		if (item.getDescription() != null) {
			form.setDescription(item.getDescription());
		}
		model.addAttribute("item", item);

		List<String> bigList = selectCategoryService.findBigAll();
		model.addAttribute("bigList", bigList);

		List<Category> middleList = selectCategoryService.getChildList(form.getBigName() + "/", 2);
		model.addAttribute("middleList", middleList);

		List<Category> smallList = selectCategoryService
				.getChildList(form.getBigName() + "/" + form.getMiddleName() + "/", 3);
		model.addAttribute("smallList", smallList);
		return "edit";
	}

	/**
	 * 商品情報の変更を行い、商品詳細画面に遷移します.
	 * 
	 * @param form 入力された情報を受け取るフォーム
	 * @param id   商品id
	 * @return 商品詳細画面
	 */
	@PostMapping("/edit")
	public String edit(@Validated UpdateItemForm form, BindingResult result, int id, Model model) {
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
		int conditionId = 0;
		if (form.getConditionId() != null) {
			conditionId = Integer.parseInt(form.getConditionId());
		}
		if (!(1 <= conditionId && conditionId <= 5)) {
			FieldError fieldError = new FieldError(result.getObjectName(), "conditionId", "error:may not be empty");
			result.addError(fieldError);
		}
		// categoryの値が不正な場合にエラーを追加（孫カテゴリまで選択されていない場合）
		if (form.getSmallName() == null || form.getSmallName().equals("0")) {
			FieldError fieldError = new FieldError(result.getObjectName(), "smallName", "error:may not be empty");
			result.addError(fieldError);
		}
		if (result.hasErrors()) {
			return showEditItem(form, Integer.parseInt(form.getId()), model);
		}
		updateItemService.updateItem(form);

		return "redirect:/showDetail?id=" + id;
	}

}
