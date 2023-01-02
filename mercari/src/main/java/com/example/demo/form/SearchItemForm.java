package com.example.demo.form;

/**
 * 商品検索時に使用するフォーム.
 * 
 * @author inagakisaia
 *
 */
public class SearchItemForm {

	/** 名前 */
	private String name;
	/** 親カテゴリID */
	private String bigId;
	/** 子カテゴリID */
	private String middleId;
	/** 孫カテゴリID */
	private String categoryId;
	/** ブランド名 */
	private String brand;

	@Override
	public String toString() {
		return "SearchItemForm [name=" + name + ", bigId=" + bigId + ", middleId=" + middleId + ", categoryId="
				+ categoryId + ", brand=" + brand + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

	public String getMiddleId() {
		return middleId;
	}

	public void setMiddleId(String middleId) {
		this.middleId = middleId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
