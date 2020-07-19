package com.sankai.inside.crm.web.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.CustomerRemindList;
import com.sankai.inside.crm.entity.CustomerStatus;
import com.sankai.inside.crm.entity.DictEasy;
import com.sankai.inside.crm.entity.HomeCount;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.CustomerRemindService;
import com.sankai.inside.crm.service.ICustomerService;
import com.sankai.inside.crm.service.ISysDictService;
import com.sankai.inside.crm.service.MessageService;
/*import com.sankai.inside.crm.web.core.SuperAccount;*/
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.FormPage;

@Controller
public class HomeController {

	@Autowired
	private AccountService accountService;

	@Resource
	private ICustomerService customerServiceImpl;

	@Resource
	private CustomerRemindService customerRemindService;
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private ISysDictService sysDictService;

	@RequestMapping(path={"index","/"},method=RequestMethod.GET)
	public String index(Model model){
		Account account=new Account();
		int loginId = UserState.getLoginId();
		account=accountService.getAccountInfo(loginId);
		model.addAttribute("messageCount", messageService.getUnreadCount());
		model.addAttribute("accountInfo",account);
		return "index";
	}

	@RequestMapping(path = "home/content", method = RequestMethod.POST)
	public String content(HttpServletRequest request, Model model, FormPage page) {
		// 统计信息
		//model.addAttribute("HomeCount", customerServiceImpl.getHomeCount());
		// 客户提醒列表
		ServiceResult<Page<CustomerRemindList>> result = customerRemindService.getList(page.getPage(),
				page.getPageSize());
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));

		return "home/_list";
	}

	@RequestMapping(path = "home/content", method = RequestMethod.GET)
	public String content(Model model, FormPage page) {

		// 统计信息
		HomeCount hc = customerServiceImpl.getHomeCount();
		model.addAttribute("HomeCount", hc);
		List<DictEasy> dictList = sysDictService.getDictByPid(36);//客户状态[数据字典]
		if(dictList!=null&&hc!=null&&hc.getCustomerStatusList()!=null)
		{
			List<CustomerStatus> csList = hc.getCustomerStatusList();
			for (DictEasy item : dictList) {
				Optional<CustomerStatus> cs = csList.stream().filter(x->x.getStatus()==item.getId()).findFirst();
				Integer val= 0;
				//boolean b = cs.isPresent();
				if(cs.isPresent()&&cs.get()!=null){
					val = cs.get().getStatusNo();
				}
				item.setCustomerNo(val);
			}
		}
		model.addAttribute("dictList",dictList);
		// 客户提醒列表
		ServiceResult<Page<CustomerRemindList>> result = customerRemindService.getList(page.getPage(),
				page.getPageSize());
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));

		return "home/content";
	}

	@RequestMapping(path = "home/content_welcome", method = RequestMethod.GET)
	public String welcome() {

		return "home/content_welcome";
	}

	/*
	 * @RequiresAuthentication
	 * 
	 * @RequiresPermissions(value = "")
	 */
	@RequestMapping(path = "home/delete.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool delete(@RequestParam("id") int id) {
		return customerRemindService.remove(id);
	}

}
