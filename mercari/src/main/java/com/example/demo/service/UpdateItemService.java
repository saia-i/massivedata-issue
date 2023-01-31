package com.example.demo.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Brand;
import com.example.demo.domain.Item;
import com.example.demo.form.UpdateItemForm;
import com.example.demo.repository.BrandRepository;
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

	@Autowired
	private BrandRepository brandRepository;

	/**
	 * 商品情報を1件取得します.
	 * 
	 * @param itemId 商品ID
	 * @return 検索された商品情報
	 */
	public Item showEditItem(int itemId) {
		Item item = itemRepository.loadJoinCategory(itemId);
		String[] categories = item.getCategoryDetail().getPath().split("/");
		item.getCategoryDetail().setBigName(categories[0]);
		item.getCategoryDetail().setMiddleName(categories[1]);
		return item;
	}

	/**
	 * 商品情報を更新します.
	 * 
	 * @param form 商品情報を受け取るフォーム
	 */
	public void updateItem(UpdateItemForm form) {
		form = Objects.requireNonNull(form);
		String categoryPath = form.getBigName() + "/" + form.getMiddleName() + "/" + form.getSmallName() + "/";
		int categoryId = categoryRepository.findIdByPath(categoryPath);
		int brandId = 0;
		if (form.getBrand() == null) {
			brandId = brandRepository.findByName("NoBrand").get().getBrandId();
		} else {
			Optional<Brand> brand = brandRepository.findByName(form.getBrand());
			if (brand == null) {
				brandId = brandRepository.insert(form.getBrand());
			} else {
				brandId = brand.get().getBrandId();
			}
		}

		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setId(Integer.parseInt(form.getId()));
		item.setCategoryId(categoryId);
		item.setPrice(Double.parseDouble(form.getPrice()));
		item.setShipping(0);
		item.setBrandId(brandId);

		itemRepository.update(item);

	}

}
