package com.sankai.inside.crm.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sankai.inside.crm.entity.ContactShare;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.ContactShareService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.ContactShareForm;

@Controller
@RequestMapping("contactShare")
public class ContactShareController {
	@Autowired
	private ContactShareService contactShareService;// 联系人共享

	@RequestMapping(path = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool add(ContactShareForm dto, HttpServletRequest request) throws Exception {
		ServiceResultBool result = new ServiceResultBool();
		int logidId = UserState.getLoginId();
		List<ContactShare> contactShareList = new ArrayList<ContactShare>();
		List<String> allowAccountIdArray = dto.getAllowAccountId();
		
		//获取联系人Id
		List<String> contactIdArray = dto.getContactId();	
		if (allowAccountIdArray == null || allowAccountIdArray.size() == 0) {
			result.setMsg("请选择要共享的用户");
			result.setSuccess(false);
			return result;
		}
		if (contactIdArray == null || contactIdArray.size() == 0) {
			result.setMsg("请选择被共享的用户");
			result.setSuccess(false);
			return result;
		} 
		
		for (String conId : contactIdArray) {
			String [] conIdSplit=conId.split("\\|");
			Integer contactId = Integer.valueOf(conIdSplit[0]);
			Integer customerId=Integer.valueOf(conIdSplit[1]);
			for (String aaid : allowAccountIdArray) {
				Integer allowAccountId = Integer.valueOf(aaid);				
				if (contactShareService.checkExists(contactId, allowAccountId) > 0) {
					continue;
				}
				
				ContactShare conShare = new ContactShare();
				conShare.setAllowAccountId(allowAccountId);
				conShare.setContactId(contactId);
				conShare.setCustomerId(customerId);
				conShare.setCreateTime(new Date());
				conShare.setAccountId(logidId);// 登录人id
				contactShareList.add(conShare);//自己不能分享给自己
			}
		}
		
		contactShareService.insertContactShare(contactShareList);
		result.setSuccess(true);
		result.setMsg("共享成功");
		return result;
	}
}
