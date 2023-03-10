package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * オリジナル情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
@JsonPropertyOrder({ "train_id", "name", "item_condition_id", "category_name", "brand_name", "price", "shipping",
		"item_description" })
public class Original {

	/** ID */
	private String train_id;
	/** 名前 */
	private String name;
	/** 状態 */
	private String item_condition_id;
	/** カテゴリ */
	private String category_name;
	/** ブランド名 */
	private String brand_name;
	/** 価格 */
	private String price;
	/** 配送情報 */
	private String shipping;
	/** 説明 */
	private String item_description;

	public Original(String train_id, String name, String item_condition_id, String category_name, String brand_name,
			String price, String shipping, String item_description) {
		super();
		this.train_id = train_id;
		this.name = name;
		this.item_condition_id = item_condition_id;
		this.category_name = category_name;
		this.brand_name = brand_name;
		this.price = price;
		this.shipping = shipping;
		this.item_description = item_description;
	}

	public Original() {

	}

	public String getTrain_id() {
		return train_id;
	}

	public void setTrain_id(String train_id) {
		this.train_id = train_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem_condition_id() {
		return item_condition_id;
	}

	public void setItem_condition_id(String item_condition_id) {
		this.item_condition_id = item_condition_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
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

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	@Override
	public String toString() {
		return "Original [train_id=" + train_id + ", name=" + name + ", item_condition_id=" + item_condition_id
				+ ", category_name=" + category_name + ", brand_name=" + brand_name + ", price=" + price + ", shipping="
				+ shipping + ", item_description=" + item_description + "]";
	}

}
//-- original
//create table original (
//  id integer not null
//  , name character varying(255)
//  , condition_id integer
//  , category_name character varying(255)
//  , brand character varying(255)
//  , price double precision
//  , shipping integer
//  , description text
//  , constraint original_PKC primary key (id)
//) ;