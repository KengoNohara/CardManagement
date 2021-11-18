package com.example.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Card")
public class CardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@NotBlank
	@Size(max = 10)
	@Column
	private String factory; // 社名保存フィールド//

	@Column(length = 100, nullable = true)
	private String path;

	@Transient
	private MultipartFile file;

	@NotNull
	@Column
	private String date; // 日付データ保存フィールド//

	@NotBlank
	@Size(max = 30)
	@Column
	private String name; // 名前保存フィールド//

	@Size(max = 500)
	@Column
	private String notes; // 特記事項保存フィールド//

	@NotBlank
	@Size(max = 10)
	@Column
	private String state;// 役職保持フィールド

	@NotBlank
	@Size(max = 10)
	@Column
	private String relation;

	// トップページ用
	@Transient
	private String Count;// 会社の数を保有するメソッド

	@Transient
	private Integer[] inputMultiCheck;// 拡張機能のメソッド_消さない

	@Transient
	private Integer[] nninputMultiCheck;// 拡張機能のメソッド_消さない

	@Transient
	private Integer[] imputMultiCheck;// 一括削除用のフィールド

	// -----------以下Getter_Setter生成フィールド-----------//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Integer[] getInputMultiCheck() {
		return inputMultiCheck;
	}

	public void setInputMultiCheck(Integer[] inputMultiCheck) {
		this.inputMultiCheck = inputMultiCheck;
	}

	public Integer[] getNninputMultiCheck() {
		return nninputMultiCheck;
	}

	public void setNninputMultiCheck(Integer[] nninputMultiCheck) {
		this.nninputMultiCheck = nninputMultiCheck;
	}

	public Integer[] getImputMultiCheck() {
		return imputMultiCheck;
	}

	public void setImputMultiCheck(Integer[] imputMultiCheck) {
		this.imputMultiCheck = imputMultiCheck;
	}

	public String getCount() {
		return Count;
	}

	public void setCount(String count) {
		Count = count;
	}

}
