package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public Integer insert(Category category) {
		String sql = "INSERT INTO category (parent,name,name_all) VALUES(:parent,:name,:nameAll)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String keyColumnNames[] = { "id" };
		template.update(sql, param, keyHolder, keyColumnNames);
		Integer id = keyHolder.getKey().intValue();
		return id;
	}

}
