package com.example.demo.domain;

import java.util.List;

/**
 * 親カテゴリーを表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class Big {

	/** ID */
	private int id;
	/** 名前 */
	private String name;
	/** 子カテゴリーリスト */
	private List<Middle> middle;

	public Big() {

	}

	public Big(String categoryName, List<Middle> middle) {
		super();
		this.name = categoryName;
		this.middle = middle;
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

	public List<Middle> getMiddle() {
		return middle;
	}

	public void setMiddle(List<Middle> middle) {
		this.middle = middle;
	}

	@Override
	public String toString() {
		return "Big [id=" + id + ", name=" + name + ", middle=" + middle + "]";
	}

}
