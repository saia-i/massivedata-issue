package com.example.demo.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	public static final RowMapper<Brand> BRAND_ROW_MAPPER = new BeanPropertyRowMapper<>(Brand.class);

	/**
	 * ブランドIDからブランド情報を一件検索します.
	 * 
	 * @param brandId ブランドID
	 * @return 検索されたブランド情報
	 */
	public Brand load(int brandId) {
		String sql = "SELECT id,name FROM brands WHERE brand_id=:brandId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("brandId", brandId);
		Brand brand = template.queryForObject(sql, param, BRAND_ROW_MAPPER);
		return brand;
	}

	/**
	 * ブランド名からブランド情報を一件検索します,
	 * 
	 * @param brandName ブランド名
	 * @return 検索されたブランド情報
	 */
	public Optional<Brand> findByName(String brandName) {
		brandName = Objects.requireNonNull(brandName);
		String sql = "SELECT brand_id,name FROM brands WHERE name ILIKE :name;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", brandName);
		List<Brand> brandList = template.query(sql, param, BRAND_ROW_MAPPER);
		if (brandList.size() == 0) {
			return null;
		}
		return Optional.ofNullable(brandList.get(0));
	}

	/**
	 * ブランド情報を挿入します.
	 * 
	 * @param brandName ブランド名
	 * @return 採番されたbrandID
	 */
	public int insert(String brandName) {
		brandName = Objects.requireNonNull(brandName);
		String sql = "INSERT INTO brands (name) VALUES (:name);";
		SqlParameterSource param = new MapSqlParameterSource("name", brandName);

		// 採番されたidを取得する
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String keyColumnNames[] = { "id" };
		template.update(sql, param, keyHolder, keyColumnNames);
		Integer brandId = keyHolder.getKey().intValue();

		return brandId;
	}

}
