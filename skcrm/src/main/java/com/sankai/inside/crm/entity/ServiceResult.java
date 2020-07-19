package com.sankai.inside.crm.entity;

public class ServiceResult<T extends Object>  {
	
	private T data;
	private boolean success;
	private String msg;
	
	public ServiceResult() {
		// TODO 自动生成的构造函数存根
	}
	
	public ServiceResult(T data) {
		super();
		this.success = true;
		this.data = data;
	}
	

	public ServiceResult(String msg) {
		super();
		this.success = false;
		this.msg = msg;
	}
	
	
	public ServiceResult(boolean success,T data,  String msg) {
		super();
		this.data = data;
		this.success = success;
		this.msg = msg;
	}


	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
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




	@Override
	public String toString() {
		return "ServiceResult [data=" + data + ", success=" + success + ", msg=" + msg + "]";
	}
	
	
	
	
}
