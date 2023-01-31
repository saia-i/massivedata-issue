package com.example.demo.domain;

/**
 * 商品情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class Item {

	/** ID */
	private int id;
	/** 名前 */
	private String name;
	/** 状態 */
	private Integer conditionId;
	/** カテゴリ */
	private Integer categoryId;
	/** ブランド */
	private Integer brandId;
	/** 価格 */
	private double price;
	/** 配送情報 */
	private Integer shipping;
	/** 説明 */
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getConditionId() {
		return conditionId;
	}

	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", conditionId=" + conditionId + ", categoryId=" + categoryId
				+ ", brandId=" + brandId + ", price=" + price + ", shipping=" + shipping + ", description="
				+ description + "]";
	}

}
