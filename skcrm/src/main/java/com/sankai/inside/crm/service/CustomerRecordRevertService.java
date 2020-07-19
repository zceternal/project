package com.sankai.inside.crm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.sankai.inside.crm.dao.ICustomerRecordRevertDAO;
import com.sankai.inside.crm.entity.CustomerRecordRevert;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.web.core.UserState;

@Service
public class CustomerRecordRevertService {

	
	@Autowired
	private ICustomerRecordRevertDAO customerRecordRevertDAO;

	public List<CustomerRecordRevert> getCustomerRecordRevertList(int recordId) {
		
		//PageHelper.startPage(page, pageSize, true);
		List<CustomerRecordRevert> list = (List<CustomerRecordRevert>) customerRecordRevertDAO.selectBy(recordId);
		return list;
	}
	

	public ServiceResult add(CustomerRecordRevert model)
			throws Exception {
		ServiceResult<CustomerRecordRevert> result=new ServiceResult<CustomerRecordRevert>();
		model.setCreateTime(new Date());
		model.setAccountId(UserState.getLoginId());
		customerRecordRevertDAO.insertCustomerRecordRevert(model);
		 result.setSuccess(true);
		result.setData(model);

		return result;
	}
	
	
}
