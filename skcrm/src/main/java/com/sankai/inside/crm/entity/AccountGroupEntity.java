package com.sankai.inside.crm.entity;

import java.util.Date;

/**
 * 描述：</b>用户群组：用于大客户公海抢占权限<
 * @author：ZZC
 * @version:2016-11-11
 */
public class AccountGroupEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 *
	 */
	private Integer id;
	/**
	 *群组名称
	 */
	private String name;
	/**
	 *组成员集合
	 */
	private String accountList;
	/**
	 *组成员id集合
	 */
	private String accountIdList;
	/**
	 *群组说明
	 */
	private String remark;
	/**
	 *创建时间
	 */
	private Date createTime;
	/**
	 *创建人
	 */
	private String createName;
	/**
	 *最后修改人
	 */
	private String lastModifyName;
	
	
	public String getAccountIdList() {
		return accountIdList;
	}

	public void setAccountIdList(String accountIdList) {
		this.accountIdList = accountIdList;
	}

	/**
	 *获取
	 */
	public Integer getId(){
		return this.id;
	}
	
	/**
	 *设置
	 */
	public void setId(Integer id){
		this.id = id;
	}
	
	/**
	 *获取群组名称
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 *设置群组名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *获取组成员集合
	 */
	public String getAccountList(){
		return this.accountList;
	}
	
	/**
	 *设置组成员集合
	 */
	public void setAccountList(String accountList){
		this.accountList = accountList;
	}
	
	/**
	 *获取群组说明
	 */
	public String getRemark(){
		return this.remark;
	}
	
	/**
	 *设置群组说明
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/**
	 *获取创建时间
	 */
	public Date getCreateTime(){
		return this.createTime;
	}
	
	/**
	 *设置创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 *获取创建人
	 */
	public String getCreateName(){
		return this.createName;
	}
	
	/**
	 *设置创建人
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	
	/**
	 *获取最后修改人
	 */
	public String getLastModifyName(){
		return this.lastModifyName;
	}
	
	/**
	 *设置最后修改人
	 */
	public void setLastModifyName(String lastModifyName){
		this.lastModifyName = lastModifyName;
	}
	
}

