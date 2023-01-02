package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Big;
import com.example.demo.domain.CategoryDetail;
import com.example.demo.domain.Item;
import com.example.demo.domain.Middle;
import com.example.demo.domain.Small;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

/**
 * 商品一覧表示に関する業務処理を行うサービス.
 * 
 * @author inagakisaia
 *
 */
@Service
public class ShowItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 開始位置から30件の商品情報を取得します.
	 * 
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	public List<Item> showList(int startId) {

		List<Item> itemList = itemRepository.findPartOfContent(startId);
		return itemList;
	}

	/**
	 * 商品情報の総件数を取得します.
	 * 
	 * @return 総件数
	 */
	public int count() {
		int count = itemRepository.count();
		return count;
	}

	/**
	 * 親カテゴリを全件取得します.
	 * 
	 * @return 親カテゴリリスト
	 */
	public List<Big> findBigAll() {
		List<Big> bigList = categoryRepository.findBigAll();
		return bigList;
	}

	/**
	 * 商品の検索をし、開始位置から30件取得します.
	 * 
	 * @param category カテゴリID
	 * @param name     名前
	 * @param brand    ブランド名
	 * @param startId  開始位置
	 * @return 検索された商品情報
	 */
	public List<Item> showItemOfSmallCategory(Integer category, String name, String brand, int startId) {
		List<Item> itemList = itemRepository.searchItemOfSmallCategory(category, name, brand, startId);
		return itemList;
	}

	/**
	 * 商品の検索をし、開始位置から30件取得します.
	 * 
	 * @param category カテゴリID
	 * @param name     名前
	 * @param brand    ブランド名
	 * @param startId  開始位置
	 * @return 検索された商品情報
	 */
	public List<Item> showItemOfMiddleCategory(Integer category, String name, String brand, int startId) {
		List<Item> itemList = itemRepository.searchItemOfMiddleCategory(category, name, brand, startId);
		List<Item> itemCategoryList = new ArrayList<>();
		for (Item item : itemList) {
			if (item.getCategory() != null) {
				CategoryDetail categoryDetail = categoryRepository.parentFindById(item.getCategory());
				item.setCategoryDetail(categoryDetail);
				itemCategoryList.add(item);
			}
		}
		return itemCategoryList;
	}

	/**
	 * 商品の検索をし、開始位置から30件取得します.
	 * 
	 * @param category カテゴリID
	 * @param name     名前
	 * @param brand    ブランド名
	 * @param startId  開始位置
	 * @return 検索された商品情報
	 */
	public List<Item> showItemOfBigCategory(Integer category, String name, String brand, int startId) {
		List<Item> itemList = itemRepository.searchItemOfBigCategory(category, name, brand, startId);
		List<Item> itemCategoryList = new ArrayList<>();
		for (Item item : itemList) {
			if (item.getCategory() != null) {
				CategoryDetail categoryDetail = categoryRepository.parentFindById(item.getCategory());
				item.setCategoryDetail(categoryDetail);
				itemCategoryList.add(item);
			}
		}
		return itemCategoryList;
	}

	/**
	 * 商品の検索をし、開始位置から30件取得します.
	 * 
	 * @param name    名前
	 * @param brand   ブランド名
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	public List<Item> showItemByNameAndBrand(String name, String brand, int startId) {
		List<Item> itemList = itemRepository.searchByNameAndBrand(name, brand, startId);
		List<Item> itemCategoryList = new ArrayList<>();
		for (Item item : itemList) {
			if (item.getCategory() != null) {
				CategoryDetail categoryDetail = categoryRepository.parentFindById(item.getCategory());
				item.setCategoryDetail(categoryDetail);
				itemCategoryList.add(item);
			}
		}
		return itemCategoryList;
	}

	/**
	 * 検索された情報の総件数を取得します.
	 * 
	 * @param category カテゴリID
	 * @param name     名前
	 * @param brand    ブランド名
	 * @return 総数
	 */
	public int countItemByMiddleCategory(Integer category, String name, String brand) {
		int count = itemRepository.countItemByMiddleCategory(category, name, brand);
		return count;
	}

	/**
	 * 検索された情報の総件数を取得します.
	 * 
	 * @param category カテゴリID
	 * @param name     名前
	 * @param brand    ブランド名
	 * @return 総数
	 */
	public int countItemBySmallCategory(Integer category, String name, String brand) {
		int count = itemRepository.countItemBySmallCategory(category, name, brand);
		return count;
	}

	/**
	 * 検索された情報の総件数を取得します.
	 * 
	 * @param category カテゴリID
	 * @param name     名前
	 * @param brand    ブランド名
	 * @return 総数
	 */
	public int countItemByBigCategory(Integer category, String name, String brand) {
		int count = itemRepository.countItemByBigCategory(category, name, brand);
		return count;
	}

	/**
	 * 検索された情報の総件数を取得します.
	 * 
	 * @param name  名前
	 * @param brand ブランド名
	 * @return 総数
	 */
	public int countItemByNameAndBrand(String name, String brand) {
		int count = itemRepository.CountItemByNameAndBrand(name, brand);
		return count;
	}

	/**
	 * 親カテゴリIDから子カテゴリを取得します.
	 * 
	 * @param parentId 親カテゴリID
	 * @return 検索された子カテゴリ情報
	 */
	public List<Middle> showMiddleListByParent(int parentId) {
		List<Middle> middleList = categoryRepository.findMiddleByParent(parentId);
		return middleList;
	}

	/**
	 * 子カテゴリIDから孫カテゴリを取得します.
	 * 
	 * @param parentId 子カテゴリID
	 * @return 検索された孫カテゴリ情報
	 */
	public List<Small> showSmallListByParent(int parentId) {
		List<Small> smallList = categoryRepository.findSmallByParent(parentId);
		return smallList;
	}

	/**
	 * ブランド名から商品情報を取得します.
	 * 
	 * @param brand   ブランド名
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	public List<Item> showItemByBrand(String brand, int startId) {
		List<Item> itemList = itemRepository.findByBrand(brand, startId);
		List<Item> itemCategoryList = new ArrayList<>();
		for (Item item : itemList) {
			if (item.getCategory() != null) {
				CategoryDetail categoryDetail = categoryRepository.parentFindById(item.getCategory());
				item.setCategoryDetail(categoryDetail);
				itemCategoryList.add(item);
			}
		}
		return itemCategoryList;
	}

	/**
	 * 検索された商品情報の総件数を取得します.
	 * 
	 * @param brand ブランド名
	 * @return 総数
	 */
	public int countByBrand(String brand) {
		int count = itemRepository.countByBrand(brand);
		return count;
	}
}
