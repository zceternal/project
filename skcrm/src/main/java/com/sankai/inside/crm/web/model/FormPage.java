package com.sankai.inside.crm.web.model;

public class FormPage {
	
	private int page = 1;
	private int pageSize = 20;
	
	
	
	public FormPage() {
		super();
	}

	public FormPage(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
