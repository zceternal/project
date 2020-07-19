package com.sankai.inside.crm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankai.inside.crm.dao.IContactShareDAO;
import com.sankai.inside.crm.entity.ContactShare;
import com.sankai.inside.crm.entity.ContactShareExistsCheck;
import com.sankai.inside.crm.entity.ServiceResultBool;

@Service
public class ContactShareService {
	@Autowired
	private IContactShareDAO contactShareDAO;

	public ServiceResultBool add(ContactShare model) {
		// ContactShare share = new ContactShare();

		// PropertyUtils.copyProperties(share, model);
		// share.setCreateTime(new Date());
		model.setCreateTime(new Date());
		contactShareDAO.insert(model);
		return new ServiceResultBool();
	}
	public ServiceResultBool update(ContactShare model) {
		
		model.setCreateTime(new Date());
		contactShareDAO.update(model);
		return new ServiceResultBool();
	} 
	public ServiceResultBool delete(int contactId,int accountId){
		contactShareDAO.delete(contactId, accountId);
		return new ServiceResultBool();
	}
	
	public Integer checkExists(int contactId, int allowAccountId) throws Exception {

		ContactShareExistsCheck check = new ContactShareExistsCheck();
		check.setContactId(contactId);
		check.setAllowAccountId(allowAccountId);
		Integer ger = contactShareDAO.checkExists(check);

		return ger;
	}

	public Integer insertContactShare(List<ContactShare> list) {
		if (list.size() == 0)
			return 1;
		Integer ger = contactShareDAO.insertContactShare(list);
		return ger;
	}
	
	public List<Integer> getIdsByAccId(Integer accId){
		return contactShareDAO.selectIdsByAccId(accId);
	}

	public int updateBatchByIds(ContactShare dto){
		return contactShareDAO.updateBatchByIds(dto);
	}
	
	public int updateTran(ContactShare dto){
		return contactShareDAO.updateTran(dto);
	}
}
