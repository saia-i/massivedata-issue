package com.example.demo.repository;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Brand;

/**
 * ブランド情報を操作するリポジトリ.
 * 
 * @author inagakisaia
 *
 */
@Repository
public class BrandRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Brand> BRAND_ROW_MAPPER = new BeanPropertyRowMapper<>(Brand.class);

	/**
	 * ブランド情報を挿入します.
	 * 
	 * @param name ブランド名
	 */
	public void insert(String name) {
		name = Objects.requireNonNull(name);
		String sql = "INSERT INTO brands (name) VALUES (:name);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		template.update(sql, param);

	}

	/**
	 * ブランド名からIDを一件検索します.
	 * 
	 * @param name ブランド名
	 * @return 検索されたブランドID
	 */
	public int findIdByName(String name) {
		name = Objects.requireNonNull(name);
		String sql = "SELECT brand_id,name FROM brands WHERE name=:name;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		Brand brand = template.queryForObject(sql, param, BRAND_ROW_MAPPER);
		return brand.getBrandId();
	}

}
