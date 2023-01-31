package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.OriginalRepository;

/**
 * ブランド情報を操作する業務処理を行うサービス.
 * 
 * @author inagakisaia
 *
 */
@Service
@Transactional
public class BrandInsertService {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private OriginalRepository originalRepository;

	/**
	 * ブランド名を挿入します.
	 */
	public void insert() {
		List<String> brandNameList = originalRepository.findBrandNameAll();
		brandNameList.add("NoBrand");
		for (String brandName : brandNameList) {
			brandRepository.insert(brandName);
		}
	}

}
