package com.example.demo;

public class CountFactoryClass {
	// List型で格納するフィールド
	String factory;
	Integer count;
	String name;
	String relation;
	String state;
	Integer id;

	// フィールドは上記のみ
	public CountFactoryClass(String factory, Integer count, String name, String relation, String state,Integer id) {
		this.factory = factory;
		this.count = count;
		this.name = name;
		this.relation = relation;
		this.state = state;
		this.id=id;
		// 上記はコンストラクタ
	}

	public CountFactoryClass(String factory, String name) {
		this.factory = factory;
		this.name = name;
		// 上記はコンストラクタ
	}

	public CountFactoryClass(String factory, Integer count) {
		this.factory = factory;
		this.count = count;
		// 上記はコンストラクタ
	}

	public CountFactoryClass(String name, String relation, String state,Integer id) {
		this.name = name;
		this.relation = relation;
		this.state=state;
		this.id=id;
	}

	// 上記はコンストラクタ
	public CountFactoryClass(String name) {
		this.name = name;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
