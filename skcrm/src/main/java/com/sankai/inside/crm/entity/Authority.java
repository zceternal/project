package com.sankai.inside.crm.entity;

public class Authority {
	
	
	
	public Authority() {
		super();
	}

	public Authority(int type, int quoteId, String moduleKey) {
		super();
		this.type = type;
		this.quoteId = quoteId;
		this.moduleKey = moduleKey;
	}

	private int id;
	
	private int type;
	
	private int quoteId;
	
	private String moduleKey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}
	
	
}
