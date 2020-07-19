package com.sankai.inside.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankai.inside.crm.dao.ICustomerLogDAO;
import com.sankai.inside.crm.entity.CustomerLog;
import com.sankai.inside.crm.entity.CustomerLogList;

@Service
public class CustomerLogService {

	@Autowired
	private ICustomerLogDAO customerLogDAO;

	public List<CustomerLogList> getList(Integer customerId) {

		return customerLogDAO.selectList(customerId);
		
	}
	
	public List<CustomerLogList> getListState(Integer customerId) {

		return customerLogDAO.selectListState(customerId);
		
	}

	public Integer add(CustomerLog model) {
		return customerLogDAO.insert(model);
		
	}
}
