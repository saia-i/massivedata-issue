package com.example.demo.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.form.RegisterUserForm;
import com.example.demo.repository.UserRepository;

/**
 * ユーザ情報を操作する業務処理を行うサービス.
 * 
 * @author inagakisaia
 *
 */
@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ユーザ情報を挿入します.
	 * 
	 * @param form ユーザ情報を受け取るフォーム
	 */
	public void insertUser(RegisterUserForm form) {
		form = Objects.requireNonNull(form);
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAuthority(1);
		userRepository.insert(user);
	}

}
