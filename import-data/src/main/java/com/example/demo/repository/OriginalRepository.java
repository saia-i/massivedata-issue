package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * オリジナル情報を操作するリポジトリ.
 * 
 * @author inagakisaia
 *
 */
@Repository
public class OriginalRepository {

	private static final RowMapper<String> CATEGORY_NAME_ROW_MAPPER = (rs, i) -> {
		String categoryName = rs.getString("category_name");
		return categoryName;
	};
	private static final RowMapper<String> BRAND_NAME_ROW_MAPPER = (rs, i) -> {
		String brandName = rs.getString("brand");
		return brandName;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * カテゴリ名を全行取り出します.
	 * 
	 * @return カテゴリ名のリスト
	 */
	public List<String> findByCategoryName() {
		String sql = "select distinct category_name from original ;";
		List<String> categoryNameList = template.query(sql, CATEGORY_NAME_ROW_MAPPER);
		return categoryNameList;

	}

	/**
	 * ブランド名を全行取り出します.
	 * 
	 * @return ブランド名のリスト
	 */
	public List<String> findBrandNameAll() {
		String sql = "select distinct brand from original ;";
		List<String> brandNameList = template.query(sql, BRAND_NAME_ROW_MAPPER);
		return brandNameList;

	}

}
