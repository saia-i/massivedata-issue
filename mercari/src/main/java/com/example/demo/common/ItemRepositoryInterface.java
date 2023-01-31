package com.example.demo.common;

import java.util.List;

import com.example.demo.domain.Item;

/**
 * ItemRepositoryのメソッドを定義するインターフェース.
 * 
 * @author inagakisaia
 *
 */
public interface ItemRepositoryInterface {

	/**
	 * 商品情報を挿入します.
	 * 
	 * @param item 商品情報
	 * @return 採番されたID
	 */
	Integer insertNewItem(Item item);

	/**
	 * 商品情報を更新します.
	 * 
	 * @param item 商品情報
	 */
	void update(Item item);

	/**
	 * 指定された開始位置から30件の商品情報を取得します.
	 * 
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	List<Item> findPartOfContent(int startId);

	/**
	 * IDから商品情報を一件検索します.
	 * 
	 * @param itemId 商品ID
	 * @return 検索された商品情報
	 */
	Item loadJoinCategory(int itemId);

	/**
	 * 渡された情報から商品情報を検索します.
	 * 
	 * @param categoryPath カテゴリのパス
	 * @param name         商品名
	 * @param brand        ブランド名
	 * @param startId      開始位置
	 * @return 検索された商品情報
	 */
	List<Item> search(String categoryPath, String name, String brand, int startId);

	/**
	 * ブランド名から商品情報を完全一致検索し、開始位置から30件取得します.
	 * 
	 * @param brand   ブランドID
	 * @param startId 開始位置
	 * @return 検索された商品情報
	 */
	List<Item> findByBrand(int brandId, int startId);

}
