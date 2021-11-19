package com.example.demo;

public class CountFactoryClass {
	// List型<>で格納するためのフィールド
	String factory;
	Integer count;
	String name;
	String relation;
	String state;
	Integer id;

	// フィールドは上記のみ
	public CountFactoryClass(String factory, Integer count, String name, String relation, String state, Integer id) {
		this.factory = factory;
		this.count = count;
		this.name = name;
		this.relation = relation;
		this.state = state;
		this.id = id;
		// ↑重複しないor最初に出てくる重複した社名テーブル取得をする際に使うコンストラクタ
	}

	public CountFactoryClass(String name, String relation, String state, Integer id) {
		this.name = name;
		this.relation = relation;
		this.state = state;
		this.id = id;
		// ↑社名が重複していた場合のコンストラクタ
	}

	// -----以下GetterとSetter

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
