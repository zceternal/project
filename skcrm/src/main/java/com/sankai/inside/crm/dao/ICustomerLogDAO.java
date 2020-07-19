package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.CustomerLog;
import com.sankai.inside.crm.entity.CustomerLogList;

public interface ICustomerLogDAO {
	
	/**
	 * 根据客户id 查询全部客户日志记录
	 * @param customerId 客户id
	 * @return
	 */
	public List<CustomerLogList> selectList(Integer customerId) ;
	
	/**
	 * 根据客户id 查询全部客户日志记录加上状态
	 * @param customerId 客户id
	 * @return
	 */
	public List<CustomerLogList> selectListState(Integer customerId) ;
	
	/**
	 * 新增日志记录
	 * @param model
	 * @return
	 */
	public Integer insert(CustomerLog model);

}
