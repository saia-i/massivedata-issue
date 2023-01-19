package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Big;
import com.example.demo.domain.Middle;
import com.example.demo.domain.Small;
import com.example.demo.repository.CategoryRepository;

/**
 * カテゴリプルダウンの操作に関する業務処理を行うサービス.
 * 
 * @author inagakisaia
 *
 */
@Service
@Transactional
public class SelectCategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 親カテゴリを全件取得します.
	 * 
	 * @return 親カテゴリのリスト
	 */
	public List<Big> findBigAll() {
		List<Big> bigList = categoryRepository.findBigAll();
		return bigList;
	}

	/**
	 * 親カテゴリに紐づく子カテゴリを取得します.
	 * 
	 * @param parentId 親カテゴリID
	 * @return 検索された子カテゴリ情報
	 */
	public List<Middle> findMiddleByParent(int parentId) {
		List<Middle> middleList = categoryRepository.findMiddleByParent(parentId);
		return middleList;
	}

	/**
	 * 子カテゴリに紐づく孫カテゴリを取得します.
	 * 
	 * @param parentId 子カテゴリID
	 * @return 検索された孫カテゴリ情報
	 */
	public List<Small> findSmallByParent(int parentId) {
		List<Small> smallList = categoryRepository.findSmallByParent(parentId);
		return smallList;
	}

}
