package com.example.demo.form;

/**
 * カテゴリー情報を受け取るフォーム.
 * 
 * @author inagakisaia
 *
 */
public class CategoryForm {

	/** 選択された親カテゴリID */
	private String selectBig;
	/** 選択された子カテゴリID */
	private String selectMiddle;
	/** 選択された孫カテゴリID */
	private String selectSmall;

	public String getSelectBig() {
		return selectBig;
	}

	public void setSelectBig(String selectBig) {
		this.selectBig = selectBig;
	}

	public String getSelectMiddle() {
		return selectMiddle;
	}

	public void setSelectMiddle(String selectMiddle) {
		this.selectMiddle = selectMiddle;
	}

	public String getSelectSmall() {
		return selectSmall;
	}

	public void setSelectSmall(String selectSmall) {
		this.selectSmall = selectSmall;
	}

	@Override
	public String toString() {
		return "CategoryForm [selectBig=" + selectBig + ", selectMiddle=" + selectMiddle + ", selectSmall="
				+ selectSmall + "]";
	}

}
