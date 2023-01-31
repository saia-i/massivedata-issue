package com.example.demo.domain;

/**
 * カテゴリ情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class Category {

	/** ID */
	private Integer categoryId;
	/** 名前 */
	private String name;
	/** パス */
	private String path;
	/** 階層 */
	private Integer hierarchy;

	public Category() {

	}

	public Category(Integer categoryId, String name, String path, Integer hierarchy) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.path = path;
		this.hierarchy = hierarchy;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", path=" + path + ", hierarchy=" + hierarchy
				+ "]";
	}

}
