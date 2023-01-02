package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Original;
import com.example.demo.repository.ItemRepository;

/**
 * 商品情報を取得する業務処理を行うサービス.
 * 
 * @author inagakisaia
 *
 */
@Service
public class ShowDetailService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品情報を1件取得します.
	 * 
	 * @param itemId 商品ID
	 * @return 商品情報
	 */
	public Original showDetail(int itemId) {
		Original original = itemRepository.loadOfCategoryNameAll(itemId);
		return original;
	}

}
