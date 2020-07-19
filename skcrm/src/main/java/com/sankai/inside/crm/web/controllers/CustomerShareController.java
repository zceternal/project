package com.sankai.inside.crm.web.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sankai.inside.crm.core.mail.SpringMailSender;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.ContactShare;
import com.sankai.inside.crm.entity.CustomerContact;
import com.sankai.inside.crm.entity.CustomerLog;
import com.sankai.inside.crm.entity.CustomerShareCusDTO;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.DeptTable;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.entity.UpdateCustomerShare;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.ContactShareService;
import com.sankai.inside.crm.service.CustomerLogService;
import com.sankai.inside.crm.service.ICustomerShareService;
import com.sankai.inside.crm.service.UtilService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.FormPage;

@Controller

public class CustomerShareController {
	@Resource
	private ICustomerShareService customerShareService;

	@Autowired
	private ContactShareService contactShareService;// 联系人共享

	@Resource
	private CustomerLogService customerLogService;// 客户日志管理

	@Resource
	private AccountService accountService;// 用户服务
	@Resource
	private SpringMailSender send;// 发送邮件

	@RequestMapping(value = "customer_share/share", method = RequestMethod.GET)
	public ModelAndView share(HttpServletRequest request,CustomerShareCusDTO dto, FormPage page) {
		ModelAndView mav = new ModelAndView();
		String[] idsArray = dto.getCusids().split(",");
		String customerNames = request.getParameter("cusnames");
		mav.addObject("cusnames", customerNames);
		String isRemove = dto.getTypeName();// 是否显示移除
		// 获取要共享的客户
		List<CustomerShareCusDTO> cusList = new ArrayList<CustomerShareCusDTO>();
		if (idsArray.length > 0) {
			for (String cusidStr : idsArray) {
				if (cusidStr != null) {
					Integer cusid = Integer.valueOf(cusidStr);
					try {
						CustomerShareCusDTO cus = this.customerShareService.findCusById(cusid);

						// 根据客户Id获取联系人集合
						List<CustomerContact> contacts = customerShareService.contactByCuId(cusid);
						cus.setContacts(contacts);

						cusList.add(cus);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		// 列出所有部门
		try {
			List<DeptTable> deptAcctsList = this.customerShareService.getDeptTable();
			mav.addObject("deptAcctsList", deptAcctsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("cusList", cusList);
		mav.addObject("isRemove", isRemove);
		mav.setViewName("customer/customer_share");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "customer_share/share", method = RequestMethod.POST)
	public ServiceResultBool goShare(HttpServletRequest request) {
		ServiceResultBool result = new ServiceResultBool();
		List<CustomerShareDTO> list1 = new ArrayList<CustomerShareDTO>();
		List<ContactShare> conShareList = new ArrayList<ContactShare>();// 联系人共享集合
		String[] allowAccountIdArray = request.getParameterValues("allowAccountId");
		String[] contactIdArray = request.getParameterValues("contactId");// 获取该客户关联的联系人

		String[] customerIdArray = request.getParameterValues("customerId");
		String customerNames = request.getParameter("cusnames");
		String isShare = request.getParameter("isShare");// 是否共享 1：共享，0不共享
		Boolean isShareSuccess = false;
		if (allowAccountIdArray == null || allowAccountIdArray.length == 0) {
			result.setMsg("请选择被共享的用户");
			result.setSuccess(false);
			return result;
		}
		if (customerIdArray == null || customerIdArray.length == 0) {
			result.setMsg("请选择要共享的客户");
			result.setSuccess(false);
			return result;
		}

		try {
			for (String cusId : customerIdArray) {
				Integer customerId = Integer.valueOf(cusId);
				// 新增客户日志
				opeCustomerLog(customerId, UtilService.ArrayTranString(allowAccountIdArray, ','), 1);

				for (String aaid : allowAccountIdArray) {

					Integer allowAccountId = Integer.valueOf(aaid);
					// 分享联系人给客户
					if (contactIdArray != null) {

						for (String contaIdItem : contactIdArray) {
							String[] val = contaIdItem.split(",");
							if (val != null && val.length > 0 && val[0].equals(cusId)) {// 判断联系人所属客户id是否等于循环的客户id
								Integer contactId = Integer.valueOf(val[1]);
								if (contactShareService.checkExists(contactId, allowAccountId) > 0) {
									continue;
								}

								ContactShare conShareDto = new ContactShare();
								conShareDto.setAllowAccountId(allowAccountId);
								conShareDto.setContactId(contactId);
								conShareDto.setCreateTime(new Date());
								conShareDto.setCustomerId(customerId);
								conShareDto.setAccountId(UserState.getLoginId());// 登录人id
								conShareList.add(conShareDto);
							}
						}
					}
					// 客户分享
					if (this.customerShareService.checkExists(allowAccountId, customerId)) {
						UpdateCustomerShare cusSharModel = new UpdateCustomerShare();
						cusSharModel.setCustomerId(customerId);
						cusSharModel.setAllowAccountId(allowAccountId);
						cusSharModel.setIsShare(Integer.valueOf(isShare));
						customerShareService.updateCusIsShare(cusSharModel);
						continue;
					}
					CustomerShareDTO dto = new CustomerShareDTO();
					dto.setAllowAccountId(allowAccountId);
					dto.setCustomerId(customerId);
					dto.setIsShare(Integer.valueOf(isShare));
					// 客户来源
					dto.setIsForm(2);
					list1.add(dto);

				}
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("共享失败");
			return result;
		}
		try {
			contactShareService.insertContactShare(conShareList);// 添加联系人共享数据
			this.customerShareService.insertCustomerShare(list1);
			isShareSuccess = true;

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("error1603171753");
			e.printStackTrace();
			return result;
		}
		// 转移成功邮件通知
		String sendErrorName = "";// 发送邮件失败的人
		for (String allowAccountId : allowAccountIdArray) {
			Account account = accountService.getAccountInfo(Integer.parseInt(allowAccountId));// 当前用户信息
			String email = account.getEmail();
			String title = "共享客户";
			if(StringUtils.isEmpty(customerNames))customerNames = "";
			else customerNames = customerNames.substring(0, customerNames.length()-1);
			String content = String.format("%s 把客户【%s】共享给您，请查看CRM系统",UserState.getLoginName(),customerNames);
			try {
				send.sendMail(UserState.getLoginName(), email, title, content);
			} catch (UnsupportedEncodingException | MessagingException e) {
				sendErrorName += account.getName() + "、";
				continue;
			}
		}
		if (!sendErrorName.isEmpty()) {
			result.setSuccess(true);
			if(StringUtils.isEmpty(sendErrorName))sendErrorName = "";
			else sendErrorName = sendErrorName.substring(0, sendErrorName.length()-1);
			result.setMsg("共享成功-发送邮件到" + sendErrorName + "失败，请检查员工邮箱是否正确！");
			return result;
		}
		if (isShareSuccess) {
			result.setSuccess(true);
			result.setMsg("共享成功-邮件已发送");
		}
		return result;
	}

	/**
	 * 新增客户日志记录
	 * 
	 * @param customerId
	 * @param accountIds
	 * @param type
	 */
	private void opeCustomerLog(Integer customerId, String accountIds, Integer type) {
		// 新增客户日志
		CustomerLog log = new CustomerLog();
		log.setCustomerId(customerId);
		log.setAccountIds(accountIds);
		log.setCreateId(UserState.getLoginId());
		log.setCreateTime(new Date());
		log.setType(type);

		customerLogService.add(log);
	}

}
