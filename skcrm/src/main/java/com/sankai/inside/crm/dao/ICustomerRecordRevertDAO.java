package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.CustomerRecordRevert;
import com.sankai.inside.crm.entity.Department;

public interface ICustomerRecordRevertDAO {
	/**
	 * 添加纪录
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer insertCustomerRecordRevert(CustomerRecordRevert dto) throws Exception;
	
	/**
	 * 查询纪录
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<CustomerRecordRevert> selectBy(Integer recordId);
	
}
