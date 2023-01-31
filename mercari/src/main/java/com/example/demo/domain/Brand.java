package com.example.demo.domain;

/**
 * ブランドを表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class Brand {

	/** ブランドID */
	private Integer brandId;
	/** 名前 */
	private String name;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", name=" + name + "]";
	}

}
