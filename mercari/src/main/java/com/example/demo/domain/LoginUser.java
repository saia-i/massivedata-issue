package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * ログイン者の情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private final User user;

	/**
	 * 通常のユーザ情報に加え、認可用ロールを設定する.
	 * 
	 * @param user          ユーザ情報
	 * @param authorityList 権限情報が入ったリスト
	 */
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getName(), user.getPassword(), authorityList);
		this.user = user;
	}

	/**
	 * ユーザ情報を返します.
	 * 
	 * @return ユーザ情報
	 */
	public User getUser() {
		return user;
	}
}

