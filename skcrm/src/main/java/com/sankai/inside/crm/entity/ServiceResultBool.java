package com.sankai.inside.crm.entity;

import java.io.Serializable;

public class ServiceResultBool implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean success;
	
	private String msg;

	public ServiceResultBool() {
		super();
		this.success = true;
	}

	public ServiceResultBool(String msg) {
		super();
		this.success = false;
		this.msg = msg;
	}
	
	public ServiceResultBool(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
