package com.example.demo.domain;

public class Brand {

	private Integer brandId;
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
