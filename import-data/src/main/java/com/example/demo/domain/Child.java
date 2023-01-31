package com.example.demo.domain;

import java.util.List;

public class Child {

	private String name;
	private List<String> grandChildList;

	public Child(String name, List<String> grandChildList) {
		super();
		this.name = name;
		this.grandChildList = grandChildList;
	}

	public Child() {

	}

	@Override
	public String toString() {
		return "Child [name=" + name + ", grandChildList=" + grandChildList + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGrandChildList() {
		return grandChildList;
	}

	public void setGrandChildList(List<String> grandChildList) {
		this.grandChildList = grandChildList;
	}

}
