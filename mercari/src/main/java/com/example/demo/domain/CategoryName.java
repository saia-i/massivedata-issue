package com.example.demo.domain;

/**
 * カテゴリ情報を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class CategoryName {

	/** 親カテゴリ名 */
	private String big;
	/** 子カテゴリ名 */
	private String middle;
	/** 孫カテゴリ名 */
	private String small;
	/** パス */
	private String path;

	public CategoryName() {

	}

	public CategoryName(String big, String middle, String small, String path) {
		super();
		this.big = big;
		this.middle = middle;
		this.small = small;
		this.path = path;
	}

	@Override
	public String toString() {
		return "CategoryName [big=" + big + ", middle=" + middle + ", small=" + small + ", path=" + path + "]";
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
