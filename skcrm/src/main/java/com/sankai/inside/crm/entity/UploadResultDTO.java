package com.sankai.inside.crm.entity;

public class UploadResultDTO {
	private boolean isSuccess;
	private String path;
	private String resultCode;
	private double size;//文件大小
	private String msg;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "UploadResultDTO [isSuccess=" + isSuccess + ", path=" + path + ", resultCode=" + resultCode + ", size="
				+ size + ", msg=" + msg + "]";
	}
	
	

}
