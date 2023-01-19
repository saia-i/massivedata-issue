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
	/** カテゴリID */
	private Integer category;
	/** カテゴリ詳細 */
	private CategoryDetail categoryDetail;
	/** ブランド */
	private String brand;
	/** 価格 */
	private double price;
	/** 配送情報 */
	private Integer shipping;
	/** 説明 */
	private String description;
	/** 総数 */
	private int count;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", conditionId=" + conditionId + ", category=" + category
				+ ", categoryDetail=" + categoryDetail + ", brand=" + brand + ", price=" + price + ", shipping="
				+ shipping + ", description=" + description + ", count=" + count + "]";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

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

	public CategoryDetail getCategoryDetail() {
		return categoryDetail;
	}

	public void setCategoryDetail(CategoryDetail categoryDetail) {
		this.categoryDetail = categoryDetail;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getNameAll() {
		CategoryDetail category = this.getCategoryDetail();
		return category.getBigName() + "/" + category.getMiddleName() + "/" + category.getSmallName();
	}

}
