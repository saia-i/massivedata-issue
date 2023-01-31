package com.example.demo.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

/**
 * カテゴリ情報を操作するリポジトリ.
 * 
 * @author inagakisaia
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

	private static final RowMapper<String> BIG_ROW_MAPPER = (rs, i) -> {
		return rs.getString("name");
	};

	/**
	 * パスからカテゴリIDを検索します.
	 * 
	 * @param path パス
	 * @return 検索されたカテゴリID
	 */
	public int findIdByPath(String path) {
		path = Objects.requireNonNull(path);
		String sql = "SELECT category_id,name,path,hierarchy FROM categories WHERE path=:path;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("path", path);
		Category category = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
		return category.getCategoryId();
	}

	/**
	 * 全ての親カテゴリを検索します.
	 * 
	 * @return 親カテゴリリスト
	 */
	public List<String> findBigAll() {
		String sql = "SELECT category_id,name FROM categories WHERE hierarchy=1 ORDER BY name;";
		List<String> bigNameList = template.query(sql, BIG_ROW_MAPPER);
		return bigNameList;
	}

	/**
	 * 親カテゴリに紐づく子カテゴリを全て検索します.
	 * 
	 * @param parentId 親カテゴリID
	 * @return 検索された子カテゴリリスト
	 */
	public List<Category> findChildByParent(String path, Integer hierarchy) {
		path = Objects.requireNonNull(path);
		String sql = "SELECT category_id,name,path,hierarchy FROM categories WHERE path LIKE :path AND hierarchy=:hierarchy ORDER BY name;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("path", path + "%").addValue("hierarchy",
				hierarchy);
		List<Category> childList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return childList;
	}

}
