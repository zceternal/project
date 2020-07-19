package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.CustomerStatueTimeDto;

public interface ICustomerStatusTimeDAO {
	
	/**
	 * 根据客户id 查询记录实体
	 * @param customerId 客户id
	 * @return
	 */
	public List<CustomerStatueTimeDto> selectList(Integer customerId) ;
	/**
	 * 新增
	 * @param model
	 * @return 返回最新Id
	 */
	public Integer insert(CustomerStatueTimeDto model);
	/**
	 * 根据条件修改花费天数
	 * @param model[条件：1 主键Id；2 根据客户Id和客户状态]
	 * @return
	 */
	public boolean updateDay(CustomerStatueTimeDto model);

}
