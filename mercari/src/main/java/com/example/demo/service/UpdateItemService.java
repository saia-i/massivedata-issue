package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Big;
import com.example.demo.domain.Item;
import com.example.demo.domain.Middle;
import com.example.demo.domain.Small;
import com.example.demo.form.UpdateItemForm;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

/**
 * 商品情報更新の業務処理を行うサービス.
 * 
 * @author inagakisaia
 *
 */
@Service
@Transactional
public class UpdateItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 商品情報を1件取得します.
	 * 
	 * @param itemId 商品ID
	 * @return 検索された商品情報
	 */
	public Item showEditItem(int itemId) {
		Item item = itemRepository.loadJoinCategory(itemId);
		return item;
	}

	/**
	 * 親カテゴリを全件取得します.
	 * 
	 * @return 検索された親カテゴリリスト
	 */
	public List<Big> findBigAll() {
		List<Big> bigList = categoryRepository.findBigAll();
		return bigList;
	}

	/**
	 * 親カテゴリから子カテゴリを検索します.
	 * 
	 * @param parentId 親カテゴリID
	 * @return 検索された子カテゴリリスト
	 */
	public List<Middle> showMiddleListByParent(int parentId) {
		List<Middle> middleList = categoryRepository.findMiddleByParent(parentId);
		return middleList;
	}

	/**
	 * 子カテゴリから孫カテゴリを検索します.
	 * 
	 * @param parentId 子カテゴリID
	 * @return 検索された孫カテゴリリスト
	 */
	public List<Small> showSmallListByParent(int parentId) {
		List<Small> smallList = categoryRepository.findSmallByParent(parentId);
		return smallList;
	}

	/**
	 * 商品情報を更新します.
	 * 
	 * @param form 商品情報を受け取るフォーム
	 */
	public void updateItem(UpdateItemForm form) {

		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setId(Integer.parseInt(form.getId()));
		item.setConditionId(Integer.parseInt(form.getConditionId()));
		item.setCategory(Integer.parseInt(form.getCategory()));
		item.setPrice(Double.parseDouble(form.getPrice()));
		item.setShipping(0);

		itemRepository.update(item);

	}

}
