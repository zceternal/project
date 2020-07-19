package com.sankai.inside.crm.entity;

public class SysDictSearchDTO {
	private Integer pid;
	private String keyWord;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	@Override
	public String toString() {
		return "SysDictSearchDTO [pid=" + pid + ", keyWord=" + keyWord + "]";
	}
	
}
