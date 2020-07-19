package com.sankai.inside.crm.entity;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author dyz
 *
 */
public class Message {
	
	private BigInteger id;
	
	private String title;
	
	private int type;
	
	private String content;
	
	private Date createTime;
	
	private int state;
	
	private int createId;
	
	//扩展字段，收件人
	private String receive;
	

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", type=" + type + ", content=" + content + ", createTime="
				+ createTime + ", state=" + state + ", createId=" + createId + "]";
	}
	
	
	
	
	
}
