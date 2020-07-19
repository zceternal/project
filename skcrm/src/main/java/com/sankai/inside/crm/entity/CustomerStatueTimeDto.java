package com.sankai.inside.crm.entity;

import java.util.Date;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
public class CustomerStatueTimeDto {
	/**
	 *主键
	 */
	private Integer id;
	/**
	 *客户Id
	 */
	private Integer customerId;
	/**
	 *客户状态
	 */
	private Integer status;
	/**
	 *操作时间(选择状态的时间)
	 */
	private Date operateTime;
	/**
	 *花费天数(在此状态停留的时间)
	 */
	private Integer spendDay;
	
	/**
	 *获取主键
	 */
	public Integer getId(){
		return this.id;
	}
	
	/**
	 *设置主键
	 */
	public void setId(Integer id){
		this.id = id;
	}
	
	/**
	 *获取客户Id
	 */
	public Integer getCustomerId(){
		return this.customerId;
	}
	
	/**
	 *设置客户Id
	 */
	public void setCustomerId(Integer customerId){
		this.customerId = customerId;
	}
	
	/**
	 *获取客户状态
	 */
	public Integer getStatus(){
		return this.status;
	}
	
	/**
	 *设置客户状态
	 */
	public void setStatus(Integer status){
		this.status = status;
	}
	
	/**
	 *获取操作时间(选择状态的时间)
	 */
	public Date getOperateTime(){
		return this.operateTime;
	}
	
	/**
	 *设置操作时间(选择状态的时间)
	 */
	public void setOperateTime(Date operateTime){
		this.operateTime = operateTime;
	}
	
	/**
	 *获取花费天数(在此状态停留的时间)
	 */
	public Integer getSpendDay(){
		return this.spendDay;
	}
	
	/**
	 *设置花费天数(在此状态停留的时间)
	 */
	public void setSpendDay(Integer spendDay){
		this.spendDay = spendDay;
	}
	
}

