package com.sankai.inside.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankai.inside.crm.dao.ICustomerStatusTimeDAO;
import com.sankai.inside.crm.entity.CustomerStatueTimeDto;

@Service
public class CustomerStatusTimeService {

	@Autowired
	private ICustomerStatusTimeDAO customerStatusTimeDAO;

	public List<CustomerStatueTimeDto> getList(Integer customerId){
		return customerStatusTimeDAO.selectList(customerId);
	}

	public Integer add(CustomerStatueTimeDto model){
		return customerStatusTimeDAO.insert(model);
	}

	public boolean modifyDay(CustomerStatueTimeDto model){
		return customerStatusTimeDAO.updateDay(model);
	}
}
