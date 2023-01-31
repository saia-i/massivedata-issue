package com.example.demo.domain;

import java.util.List;

public class Parent {

	private String name;
	private List<Child> childList;

	public Parent() {

	}

	public Parent(String name, List<Child> childList) {
		super();
		this.name = name;
		this.childList = childList;
	}

	@Override
	public String toString() {
		return "Parent [name=" + name + ", childList=" + childList + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Child> getChildList() {
		return childList;
	}

	public void setChildList(List<Child> childList) {
		this.childList = childList;
	}

}
