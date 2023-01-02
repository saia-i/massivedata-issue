package com.example.demo.domain;

/**
 * 孫カテゴリ情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class Small {

	/** ID */
	private int id;
	/** 親カテゴリ名 */
	private Integer parentId;
	/** 名前 */
	private String name;
	/** パス */
	private String path;

	public Small() {

	}

	public Small(String small, String path) {
		super();
		this.name = small;
		this.path = path;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Small [id=" + id + ", parentId=" + parentId + ", name=" + name + ", path=" + path + "]";
	}

}
