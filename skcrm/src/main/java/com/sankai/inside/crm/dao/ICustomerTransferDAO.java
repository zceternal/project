package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.CustomerShareAccDTO;
import com.sankai.inside.crm.entity.CustomerShareCusDTO;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.CustomerShareExistsCheckDTO;
import com.sankai.inside.crm.entity.CustomerTransfer;
import com.sankai.inside.crm.entity.DeptDTO;


public interface ICustomerTransferDAO {

	/**
	 * 新增客户转移
	 * @param model
	 */
	public void insertTransfer(CustomerTransfer model);
	
} 
