package com.example.demo.domain;

/**
 * カテゴリ詳細を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class CategoryDetail {

	/** 親カテゴリ名 */
	private String bigName;
	/** 子カテゴリ名 */
	private String middleName;
	/** 孫カテゴリID */
	private Integer smallId;
	/** 孫カテゴリ名 */
	private String smallName;
	/** パス */
	private String path;

	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Integer getSmallId() {
		return smallId;
	}

	public void setSmallId(Integer smallId) {
		this.smallId = smallId;
	}

	public String getSmallName() {
		return smallName;
	}

	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "CategoryDetail [bigName=" + bigName + ", middleName=" + middleName + ", smallId=" + smallId
				+ ", smallName=" + smallName + ", path=" + path + "]";
	}

}
