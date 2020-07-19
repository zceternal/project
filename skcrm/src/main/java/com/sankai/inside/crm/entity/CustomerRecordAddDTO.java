package com.sankai.inside.crm.entity;

public class CustomerRecordAddDTO {
	public boolean isSuccess;//是否添加成功
	public String msg;//消息
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
