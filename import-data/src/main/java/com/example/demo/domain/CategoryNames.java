package com.example.demo.domain;

public class CategoryNames {

	private String parent;
	private String child;
	private String grandChild;

	public CategoryNames(String parent, String child, String grandChild) {
		super();
		this.parent = parent;
		this.child = child;
		this.grandChild = grandChild;
	}

	public CategoryNames() {

	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public String getGrandChild() {
		return grandChild;
	}

	public void setGrandChild(String grandChild) {
		this.grandChild = grandChild;
	}

	@Override
	public String toString() {
		return "CategoryNames [parent=" + parent + ", child=" + child + ", grandChild=" + grandChild + "]";
	}

}
