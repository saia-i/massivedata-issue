package com.example.demo.form;

/**
 * ログイン時にしようするフォーム.
 * 
 * @author inagakisaia
 *
 */
public class LoginUserForm {

	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;

	@Override
	public String toString() {
		return "LoginUserForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
