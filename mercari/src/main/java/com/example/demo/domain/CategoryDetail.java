package com.example.demo.domain;

/**
 * カテゴリ詳細を表すドメイン.
 * 
 * @author inagakisaia
 *
 */
public class CategoryDetail {

	/** 親カテゴリID */
	private Integer bigId;
	/** 親カテゴリ名 */
	private String bigName;
	/** 子カテゴリID */
	private Integer middleId;
	/** 子カテゴリ名 */
	private String middleName;
	/** 孫カテゴリID */
	private Integer smallId;
	/** 孫カテゴリ名 */
	private String smallName;

	@Override
	public String toString() {
		return "CategoryDetail [bigId=" + bigId + ", bigName=" + bigName + ", middleId=" + middleId + ", middleName="
				+ middleName + ", smallId=" + smallId + ", smallName=" + smallName + "]";
	}

	public Integer getBigId() {
		return bigId;
	}

	public void setBigId(Integer bigId) {
		this.bigId = bigId;
	}

	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}

	public Integer getMiddleId() {
		return middleId;
	}

	public void setMiddleId(Integer middleId) {
		this.middleId = middleId;
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

}
