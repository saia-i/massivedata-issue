package com.example.demo.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
	private Integer categoryId;
	/** カテゴリ詳細 */
	private CategoryDetail categoryDetail;
	/** ブランド */
	private Integer brandId;
	/** ブランド名 */
	private String brandName;
	/** 価格 */
	private double price;
	/** 配送情報 */
	private Integer shipping;
	/** 説明 */
	private String description;
	/** 総数 */
	private int count;

	public String encodedBigName() throws UnsupportedEncodingException {
		String encodedResult = URLEncoder.encode(this.getCategoryDetail().getBigName(), "UTF-8");
		return encodedResult;
	}

	public String encodedMiddleName() throws UnsupportedEncodingException {
		String encodedResult = URLEncoder.encode(this.getCategoryDetail().getMiddleName(), "UTF-8");
		return encodedResult;
	}

	public String encodedSmallName() throws UnsupportedEncodingException {
		String encodedResult = URLEncoder.encode(this.getCategoryDetail().getSmallName(), "UTF-8");
		return encodedResult;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", conditionId=" + conditionId + ", categoryId=" + categoryId
				+ ", categoryDetail=" + categoryDetail + ", brandId=" + brandId + ", brandName=" + brandName
				+ ", price=" + price + ", shipping=" + shipping + ", description=" + description + ", count=" + count
				+ "]";
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
