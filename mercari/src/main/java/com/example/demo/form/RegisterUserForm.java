package com.example.demo.form;

import javax.validation.constraints.NotBlank;

/**
 * ユーザ登録時に使用するフォーム.
 * 
 * @author inagakisaia
 *
 */
public class RegisterUserForm {

	/** 名前 */
	@NotBlank(message = "error:may not be empty")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "error:may not be empty")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "error:may not be empty")
	private String password;
	/** 確認用パスワード */
	private String confimationPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getConfimationPassword() {
		return confimationPassword;
	}

	public void setConfimationPassword(String confimationPassword) {
		this.confimationPassword = confimationPassword;
	}

	@Override
	public String toString() {
		return "RegisterUserForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", confimationPassword=" + confimationPassword + "]";
	}

}
