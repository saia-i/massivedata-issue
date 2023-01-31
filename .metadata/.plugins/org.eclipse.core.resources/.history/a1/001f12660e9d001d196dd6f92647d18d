package com.example.demo.form;

import javax.validation.constraints.NotBlank;

/**
 * 商品情報を受け取るフォーム.
 * 
 * @author inagakisaia
 *
 */
public class UpdateItemForm {

	/** ID */
	private String id;
	/** 名前 */
	@NotBlank(message="error:may not be empty")
	private String name;
	/** 状態 */
	@NotBlank(message="error:may not be empty")
	private String conditionId;
	/** カテゴリID */
	@NotBlank(message="error:may not be empty")
	private String category;
	/** 親カテゴリID */
	private String bigId;
	/** 子カテゴリID */
	private String middleId;
	/** ブランド名 */
	private String brand;
	/** 価格 */
	private String price;
	/** 配送情報 */
	private String shipping;
	/** 説明 */
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
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
		return "UpdateItemForm [id=" + id + ", name=" + name + ", conditionId=" + conditionId + ", category=" + category
				+ ", bigId=" + bigId + ", middleId=" + middleId + ", brand=" + brand + ", price=" + price
				+ ", shipping=" + shipping + ", description=" + description + "]";
	}

}
