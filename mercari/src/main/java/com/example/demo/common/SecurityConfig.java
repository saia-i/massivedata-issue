package com.example.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 
 * Spring Securityの設定を行うクラス.
 * 
 * @author inagakisaia
 *
 */
@Configuration
public class SecurityConfig {

	/**
	 * 全体に関するセキュリティ設定. (指定のファイルに対してセキュリティ設定を無視する)
	 * 
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**");
	}

	/**
	 * ログイン、ログアウトに関する設定.
	 * 
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/showDetail", "/toPage", "/search", "/index", "/registerUser",
						"/registerUser/insert", "/selectCategory/getChildList")
				.permitAll().anyRequest().authenticated();

		http.formLogin() // ログインに関する設定
				.loginPage("/index?error=false") // ログイン画面に遷移させるパス(ログイン認証が必要なパスを指定してかつログインされていないとこのパスに遷移される)
				.loginProcessingUrl("/login") // ログインボタンを押した際に遷移させるパス(ここに遷移させれば自動的にログインが行われる)
				.failureUrl("/index?error=true") // ログイン失敗に遷移させるパス
				.defaultSuccessUrl("/", true) // 第1引数:デフォルトでログイン成功時に遷移させるパス
				// 第2引数: true :認証後常に第1引数のパスに遷移
				// false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
				.usernameParameter("mailAddress") // 認証時に使用するユーザ名のリクエストパラメータ名(今回はメールアドレスを使用)
				.passwordParameter("password"); // 認証時に使用するパスワードのリクエストパラメータ名

		http.logout() // ログアウトに関する設定
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウトさせる際に遷移させるパス
				.logoutSuccessUrl("/index?error=false") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
				.deleteCookies("JSESSIONID") // ログアウト後、Cookieに保存されているセッションIDを削除
				.invalidateHttpSession(true); // true:ログアウト後、セッションを無効にする false:セッションを無効にしない
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
