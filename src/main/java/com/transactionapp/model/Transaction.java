package com.transactionapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private Double amount;
	
	private String type;
	
	@JsonProperty("parent_id")
	@Transient
	private Long parentId;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Transaction parent;
	
	@JsonIgnore
	@OneToMany(mappedBy="parent")
	private List<Transaction> children = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Transaction getParent() {
		return parent;
	}
	public void setParent(Transaction parent) {
		this.parent = parent;
	}
	public List<Transaction> getChildren() {
		return children;
	}
	public void setChildren(List<Transaction> children) {
		this.children = children;
	}
	
	
}
