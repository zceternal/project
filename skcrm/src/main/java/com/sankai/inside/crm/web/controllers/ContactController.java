
package com.sankai.inside.crm.web.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mchange.v2.async.StrandedTaskReporting;
import com.sankai.inside.crm.entity.AccountOfDept;
import com.sankai.inside.crm.entity.Contact;
import com.sankai.inside.crm.entity.ContactAutocomplate;
import com.sankai.inside.crm.entity.ContactSearch;
import com.sankai.inside.crm.entity.CustomerAutocomplate;
import com.sankai.inside.crm.entity.DeptTable;
import com.sankai.inside.crm.entity.SearchCustomerList;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.ContactService;
import com.sankai.inside.crm.service.ICustomerShareService;
import com.sankai.inside.crm.service.ISysDictService;
import com.sankai.inside.crm.service.UtilService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.ContactForm;
import com.sankai.inside.crm.web.model.ContactSearchForm;
import com.sankai.inside.crm.web.model.FormPage;
import com.sankai.inside.crm.web.model.ValidAdd;
import com.sankai.inside.crm.web.model.ValidEdit;

@Controller
@RequestMapping("contact")
public class ContactController {

	@Autowired
	private ContactService contactService;// 联系人
	@Resource
	private AccountService accountService;// 用户服务
	@Autowired
	private ISysDictService dictService;// 字典
	@Resource
	private ICustomerShareService customerShareService;// 客户分享

	@RequiresAuthentication
	@RequiresPermissions(value = "contact_list")
	@RequestMapping(path = "index", method = RequestMethod.GET)
	public String index(Model model, FormPage page,HttpServletRequest request) {

		/*
		 * List<SysDict> xsztList = sysDictService.findAllByPid(36);//销售状态
		 * List<SysDict> khlxList = sysDictService.findAllByPid(37);//客户类型
		 */
		ContactSearch search = new ContactSearch();
		int loginId = UserState.getLoginId();
		
		search.setAccountId(loginId);// 当前登录人
		search.setContactRole("0");
		search.setOrderField(null);
		search.setOrderType("");
		search.setIsqq(false);
		search.setIsemail(false);
		search.setIsphone(false);
		search.setIswechat(false);
		search.setOrderField("sort");
		search.setOrderType("desc");
		search.setCustomerType("-1");
		search.setContent("");
		search.setIsGetValue(0);
		// 获取部门的所有成员
		/*
		 * int loginId = UserState.getLoginId(); boolean isDeptLeader =
		 * accountService.isLeaderById(loginId);// 是否是部门领导人 List<AccountOfDept>
		 * accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id
		 * List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
		 * //加载列表显示 if (accList != null) { AccountOfDept first = accList.get(0);
		 * if (first.isMySelf() && first.getIsDeptManager() == 0)
		 * accListNew.add(first); else accListNew = accList; }
		 * //判断是否是领导，用来显示列表数据 if (isDeptLeader) { search.setPrincipal(null); }
		 * else { List<String> accountIdList = new ArrayList<String>();
		 * accountIdList.add(String.valueOf(loginId));
		 * search.setPrincipal(accountIdList); }
		 */
		ServiceResult<Page<Contact>> result = null;

		
		List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id
		List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
		// 不是领导人查询自己创建的联系人，是否执行
		boolean isSelect = true;
		// 加载列表显示
		List<String> accountIdList = new ArrayList<String>();
		String myself = request.getParameter("myself");
		if(myself!=null)
		{
			search.setIsGetValue(loginId);
		}
		if (accList != null) {
			AccountOfDept first = accList.get(0);
			// 是否是部门领导人
			if (first.isMySelf() && first.getIsDeptManager() == 0) {				
				isSelect = false;				
				accListNew.add(first);
				accountIdList.add(first.getId() + "");
			} else {
				for (AccountOfDept accountOfDept : accList) {
					accountIdList.add(accountOfDept.getId() + "");
				}
				accListNew = accList;

			}
		}
		
		
		if(StringUtils.isEmpty(myself))myself = "";
		else accountIdList.add(String.valueOf(loginId));// 当前登录人
		search.setPrincipal(accountIdList);
		// 查询列表
		if (isSelect) {
			result = contactService.list(search, page.getPage(), page.getPageSize());
		}
		else{
			// 根据登录id查询创建的客户-查询联系人
			result = contactService.getConByLoginId(search, page.getPage(), page.getPageSize());
		}
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		model.addAttribute("dictLxr", dictService.getDictByType(33));
		model.addAttribute("accList", accListNew);
		model.addAttribute("myself", myself);//首页点击 联系人总数
		model.addAttribute("isShowTop", false);// 是否显示置顶列

		return "contact/index";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "contact_list")
	@RequestMapping(path = "index", method = RequestMethod.POST)
	public String index(ContactSearchForm form, HttpServletRequest request, Model model, FormPage page) {

		ContactSearch search = new ContactSearch();
		search.setAccountId(UserState.getLoginId());// 当前登录人
		search.setContactRole(request.getParameter("contactRole"));// 联系人角色

		String customerType = request.getParameter("customerType") == "" ? "-1" : request.getParameter("customerType");
		search.setCustomerType(customerType);// -1默认，1已关联客户,0未关联客户
		search.setContent(request.getParameter("content"));
		search.setOrderField(request.getParameter("orderField"));
		search.setOrderType(request.getParameter("orderType"));
		// 联系人详情
		search.setIsqq(form.isQq());
		search.setIsemail(form.isEmail());
		search.setIsphone(form.isPhone());
		search.setIswechat(form.isWechat());
		search.setIsGetValue(0);
		if (request.getParameter("orderField").contains("customerNameOrder"))
			search.setOrderField("customerName");

		ServiceResult<Page<Contact>> result = null;
		// 不是领导人查询自己创建的联系人，是否执行
		boolean isSelect = true;
		// 销售负责人
		int loginId = UserState.getLoginId();
		boolean isDeptLeader = accountService.isLeaderById(loginId);// 是否是部门领导人
		List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id
		String[] accId = request.getParameterValues("accId");
		List<String> accountIdList = new ArrayList<String>();

		if (accId != null && accId.length > 0) {
			for (String str : accId) {
				accountIdList.add(str);
			}
			
		} else {
			if (isDeptLeader) {
				for (AccountOfDept accountOfDept : accList) {
					accountIdList.add(accountOfDept.getId() + "");
				}
			} else {
				accountIdList.add(String.valueOf(UserState.getLoginId()));				
				isSelect = false;				
			}
		}
		search.setPrincipal(accountIdList);
		if (isSelect) {
			search.setDeptManaer(accountService.isLeaderById(loginId));
			result = contactService.list(search, page.getPage(), page.getPageSize());
		}
		else{
			// 根据登录id查询创建的客户-查询联系人
			result = contactService.getConByLoginId(search, page.getPage(), page.getPageSize());
		}
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		if (accId == null || accId.length == accList.size())
			model.addAttribute("isShowTop", false);// 是否显示置顶列
		else
			model.addAttribute("isShowTop", true);// 是否显示置顶列

		return "contact/_list";

	}

	// 搜索客户，返回列表
	@RequestMapping(path = "customerList", method = RequestMethod.GET)
	public String customerList(Model model, FormPage page) {
		ServiceResult<Page<SearchCustomerList>> result = contactService.searchCustomerList(null, page.getPage(),
				page.getPageSize());

		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		return "contact/customerList";
	}

	// 搜索客户，返回列表
	@RequestMapping(path = "cuslist", method = RequestMethod.POST)

	public String customerList(HttpServletRequest request, Model model, FormPage page) throws Exception {
		ServiceResult<Page<SearchCustomerList>> result = contactService
				.searchCustomerList(request.getParameter("content"), page.getPage(), page.getPageSize());

		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		return "contact/customer_list";
	}

	// 新增
	@RequiresAuthentication
	@RequiresPermissions(value = "contact_add")
	@RequestMapping(path = "add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("dictLxr", dictService.getDictByType(33));
		model.addAttribute("dictLxrly", dictService.getDictByType(12));// 联系人来源
		return "contact/add";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "contact_add")
	@RequestMapping(path = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool add(@Validated({ ValidAdd.class }) ContactForm form, BindingResult result, Model model)
			throws IllegalAccessException, Exception, NoSuchMethodException {

		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}
		return contactService.add(form);
	}

	// 删除
	@RequiresAuthentication
	@RequiresPermissions(value = "contact_remove")
	@RequestMapping(path = "delete.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool delete(@RequestParam("id") int id) {
		return contactService.delete(id);
	}

	// 修改
	@RequiresAuthentication
	@RequiresPermissions(value = "contact_edit")
	@RequestMapping(path = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam Integer id, @RequestParam Integer type, Model model) {

		ServiceResult<Contact> result = contactService.selectById(id);
		if (!result.isSuccess()) {
			model.addAttribute("msg", result.getMsg());
			return "error";
		}
		String birthday = "";
		String age = "";
		if(result.getData().getBirthday()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = result.getData().getBirthday();
			 birthday = sdf.format(birth);
			 age = String.valueOf(UtilService.getAge(birth));
		}
		
		model.addAttribute("birthday", birthday);
		model.addAttribute("age", age);
		model.addAttribute("dictLxr", dictService.getDictByType(33));
		model.addAttribute("model", result.getData());
		model.addAttribute("type", type);
		return "contact/edit";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "contact_edit")
	@RequestMapping(path = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool edit(@Validated({ ValidEdit.class }) ContactForm form, BindingResult result, Model model)
			throws IllegalAccessException, Exception, NoSuchMethodException {

		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}

		return contactService.update(form);
	}

	// 查看详情
	@RequiresAuthentication
	@RequiresPermissions(value = "contact_details")
	@RequestMapping(path = "details", method = RequestMethod.GET)
	public String details(@RequestParam int id, Model model) {

		ServiceResult<Contact> result = contactService.selectById(id);
		if (!result.isSuccess()) {
			model.addAttribute("msg", result.getMsg());
			return "error";
		}
		model.addAttribute("dictLxr", dictService.getDictByType(33));
		model.addAttribute("model", result.getData());
		return "contact/details";
	}

	// 分享联系人
	@RequiresAuthentication
	@RequiresPermissions(value = "contact_share")
	@RequestMapping(path = "contactShare", method = RequestMethod.GET)
	public String contactShare(String[] id, Model model) {
		List<Contact> list = new ArrayList<Contact>();
		if (id.length > 0) {

			for (String item : id) {
				int getId = Integer.parseInt(item);
				ServiceResult<Contact> result = contactService.selectById(getId);
				list.add(result.getData());
			}
		} else {
			return "error";
		}
		model.addAttribute("model", list);// 要分享的联系人
		model.addAttribute("isOwn", UserState.getLoginId());// 分享人中不能有自己
		// 列出所有部门的列表
		try {
			List<DeptTable> deptAcctsList = this.customerShareService.getDeptTable();
			model.addAttribute("deptAcctsList", deptAcctsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "contact/contactShare";
	}

	/* 置顶 */
	@RequestMapping(path = "up.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool up(@RequestParam("id") int id, @RequestParam("sort") int sort) {
		contactService.topOperate(id, sort);
		return new ServiceResultBool();
	}
	
	/*
	 * 联系人的自动填充
	 * */
	@RequestMapping(path = "loadContact.ajax", method = RequestMethod.POST)
	@ResponseBody
	public String[] loadCon(String value,String type) {
		
		List<ContactAutocomplate> list=contactService.listAutocomplate(value,type);
		String[] arr=new String[list.size()];
		
		if (type.equals("1")) {
			for (int i = 0; i < list.size(); i++) {
				arr[i]=list.get(i).getName()+"   |   "+list.get(i).getCreateName();
			}
		}
		if (type.equals("2")) {
			for (int i = 0; i < list.size(); i++) {
				arr[i]=list.get(i).getphone()+"   |   "+list.get(i).getCreateName();
			}
		}
		
		return arr;

	}
	

}
