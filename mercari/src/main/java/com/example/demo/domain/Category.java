package com.example.demo.domain;

/**
 * カテゴリ情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class Category {

	/** ID */
	private int id;
	/** 親カテゴリID */
	private Integer parent;
	/** 名前 */
	private String name;
	/** パス */
	private String nameAll;

	public Category() {

	}

	public Category(int id, Integer parent, String name, String nameAll) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
		this.nameAll = nameAll;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", parent=" + parent + ", name=" + name + ", nameAll=" + nameAll + "]";
	}

}
