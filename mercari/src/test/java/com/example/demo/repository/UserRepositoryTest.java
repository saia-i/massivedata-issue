package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.demo.domain.User;

@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@BeforeEach
	public void testInsert() {
		System.out.println("DBにテストデータ挿入");
		User user=new User();
		user.setName("testname");
		user.setMailAddress("mail@example.com");
		user.setPassword("password");
		userRepository.insert(user);
		System.out.println("挿入完了");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"mail@example.com","notfound@example.com"})
	@DisplayName("主キー検索するテスト")
	public void testLoad(String email) {
		Optional<User> resultUser=userRepository.load(email);
		assertThat(resultUser).isNotNull();
		if(resultUser.isPresent()) {
			assertThat(resultUser.get().getName()).isEqualTo("testname");
			assertThat(resultUser.get().getPassword()).isEqualTo("password");
		}
	}
	
	@AfterEach
	public void tearDownAfterClass() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", "mail@mail.com");
		template.update("delete from users where mail_address = :mailAddress", param);
		System.out.println("入れたデータを削除しました。");
	}

}
