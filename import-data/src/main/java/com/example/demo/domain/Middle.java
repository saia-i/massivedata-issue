package com.example.demo.domain;

import java.util.List;

/**
 * 子カテゴリ情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class Middle {

	/** ID */
	private int id;
	/** 親カテゴリID */
	private Integer parentId;
	/** 名前 */
	private String name;
	/** 孫カテゴリリスト */
	private List<Small> small;

	public Middle() {

	}

	public Middle(String middle, List<Small> small) {
		super();
		this.name = middle;
		this.small = small;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Small> getSmall() {
		return small;
	}

	public void setSmall(List<Small> small) {
		this.small = small;
	}

	@Override
	public String toString() {
		return "Middle [id=" + id + ", parentId=" + parentId + ", name=" + name + ", small=" + small + "]";
	}

}
