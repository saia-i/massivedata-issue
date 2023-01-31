package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.form.SearchItemForm;
import com.example.demo.repository.ItemRepository;

/**
 * 商品一覧表示に関する業務処理を行うサービス.
 * 
 * @author inagakisaia
 *
 */
@Service
@Transactional
public class ShowItemService {

	@Autowired
	private ItemRepository itemRepository;


	/**
	 * 開始位置から30件の商品情報を取得します.
	 * 
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	public List<Item> showList(int startId) {

		List<Item> itemList = itemRepository.findPartOfContent(startId);
		for (Item item : itemList) {
			if (item.getCategoryDetail().getPath() != null) {
				String[] categories = item.getCategoryDetail().getPath().split("/");
				item.getCategoryDetail().setBigName(categories[0]);
				item.getCategoryDetail().setMiddleName(categories[1]);
			}
		}
		return itemList;
	}

	/**
	 * 検索内容に一致する商品情報を開始位置から30件取得します.
	 * 
	 * @param form      入力情報を受け取るフォーム
	 * @param brandName ブランド名
	 * @param startId   取得する商品情報リストの開始位置
	 * @return 商品リスト
	 */
	public List<Item> searchItemList(SearchItemForm form, int brandId, int startId) {
		List<Item> itemList = new ArrayList<>();
		// 未入力の場合は全件検索をさせる為、空文字をセットする
		if (form.getBrand() == null) {
			form.setBrand("");
		}
		if (form.getName() == null) {
			form.setName("");
		}

		if (brandId != 0) {// ブランドをクリックした場合
			itemList = itemRepository.findByBrand(brandId, startId);

		} else {// 検索フォームから検索した場合
			if (form.getBigName() == null || form.getBigName().equals("0")) {// カテゴリ未選択
				itemList = itemRepository.search("", form.getName(), form.getBrand(), startId);

			} else if (form.getMiddleName() == null || form.getMiddleName().equals("0")) {// 親カテゴリのみ選択
				itemList = itemRepository.search(form.getBigName() + "/", form.getName(), form.getBrand(), startId);

			} else if (form.getSmallName() == null || form.getSmallName().equals("0")) {// 子カテゴリまで選択
				itemList = itemRepository.search(form.getBigName() + "/" + form.getMiddleName() + "/", form.getName(),
						form.getBrand(), startId);

			} else {// 孫カテゴリまで選択
				itemList = itemRepository.search(
						form.getBigName() + "/" + form.getMiddleName() + "/" + form.getSmallName() + "/",
						form.getName(), form.getBrand(), startId);
			}
		}
		for (Item item : itemList) {
			if (item.getCategoryDetail().getPath() != null) {
				String[] categories = item.getCategoryDetail().getPath().split("/");
				item.getCategoryDetail().setBigName(categories[0]);
				item.getCategoryDetail().setMiddleName(categories[1]);
			}
		}
		return itemList;
	}

}
