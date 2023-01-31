package com.example.demo.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * ユーザ情報を操作するリポジトリ.
 * 
 * @author inagakisaia
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	/**
	 * ユーザ情報を挿入します.
	 * 
	 * @param user ユーザ情報
	 */
	public void insert(User user) {
		user=Objects.requireNonNull(user);
		String sql = "INSERT INTO users (name,mail_address,password,authority) VALUES (:name,:mailAddress,:password,:authority);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);

	}

	/**
	 * メールアドレスからユーザ情報を一件取得します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return 検索されたユーザ情報
	 */
	public Optional<User> load(String mailAddress) {
		mailAddress=Objects.requireNonNull(mailAddress);
		String sql = "SELECT id,name,mail_address,password,authority FROM users WHERE mail_address=:mailAddress;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		};
		return Optional.ofNullable(userList.get(0));
	}
}
