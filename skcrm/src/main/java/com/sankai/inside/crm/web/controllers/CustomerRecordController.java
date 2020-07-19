package com.sankai.inside.crm.web.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.core.utils.DelHTMLUtil;
import com.sankai.inside.crm.entity.AccountOfDept;
import com.sankai.inside.crm.entity.Address;
import com.sankai.inside.crm.entity.Contact;
import com.sankai.inside.crm.entity.ContactSearch;
import com.sankai.inside.crm.entity.ContactSearchByCusId;
import com.sankai.inside.crm.entity.Customer;
import com.sankai.inside.crm.entity.CustomerRecord;
import com.sankai.inside.crm.entity.CustomerRecordAddDTO;
import com.sankai.inside.crm.entity.CustomerRecordLogsDTO;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.CustomerShareTransDTO;
import com.sankai.inside.crm.entity.CustomerTransDTO;
import com.sankai.inside.crm.entity.LikeItDTO;
import com.sankai.inside.crm.entity.LikeItReponseDTO;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.SysDict;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.ContactService;
import com.sankai.inside.crm.service.IAddressService;
import com.sankai.inside.crm.service.ICustomerRecordService;
import com.sankai.inside.crm.service.ICustomerService;
import com.sankai.inside.crm.service.ISysDictService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.CustomerRecordSearchForm;

@Controller
public class CustomerRecordController {

	@Resource
	private ICustomerService customerServiceImpl;
	@Resource
	private ISysDictService sysDictServiceImpl;
	@Resource
	private ICustomerRecordService customerRecordServiceImpl;
	@Autowired
	private ISysDictService dictService;
	@Resource
	private IAddressService addressServiceImpl;
	@Autowired
	private ContactService contactService;// 联系人
	@Resource
	private AccountService accountService;// 用户服务
	
	@ResponseBody
	@RequestMapping(value = "cusRecord/addRecord", method = RequestMethod.POST)
	public CustomerRecordAddDTO addRecord(CustomerRecord vo) {
		CustomerRecordAddDTO result = new CustomerRecordAddDTO();
		if (vo.getRemark() == null || vo.getRemark().equals("")) {
			result.setSuccess(false);
			result.setMsg("记录内容不能为空");
			return result;
		}
		vo.setRemark(DelHTMLUtil.delHTMLTag(vo.getRemark()));
		/*else if (vo.getRemark().length() > 500) {
			result.setSuccess(false);
			result.setMsg("内容不能超过500个字符");
			return result;
		}*/
		try {
			if (this.customerRecordServiceImpl.insertRecord(vo)) {
				result.setSuccess(true);
				result.setMsg("添加成功");
			} else {
				result.setSuccess(false);
				result.setMsg("添加失败");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("error160311");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * 点赞
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "cusRecord/iLikeIt", method = RequestMethod.POST)
	public LikeItReponseDTO iLikeIt(LikeItDTO vo) {

		Integer loginId = UserState.getLoginId();
		Integer recordId = vo.getRecordId();
		LikeItReponseDTO result = this.customerRecordServiceImpl.like(loginId, recordId);
		return result;
	}

	@RequestMapping(value = "cusRecord/refreshAllow", method = RequestMethod.POST)
	public ModelAndView refreshAllow(CustomerShareDTO dto, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		// 根据客户ID查询客户的共享信息
		try {
			List<CustomerShareTransDTO> customerShares = this.customerServiceImpl.findAllows(dto.getCustomerId());
			mav.addObject("customerShares", customerShares);
			mav.setViewName("customer/allow_list");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "cusRecord/refreshContact", method = RequestMethod.POST)
	public ModelAndView refreshContact(CustomerShareDTO dto, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Integer customerId = dto.getCustomerId();
		// 根据客户ID查询客户的共享信息
		try {
			/*// 查询共享的客户联系人
			List<Contact> contacts = null;
			if (this.customerServiceImpl.checkIsOwn(customerId)) {// 如果登录人是第一负责人，则列出所有的联系人
				contacts = this.customerServiceImpl.findContactInfoByCustoemrId(customerId);
			} else {// 否则列出共享的联系人
				contacts = this.customerServiceImpl.findContactShared(customerId);
			}*/ 
			
			
			// yxb 修改的问题 ---查询共享的客户联系人
			// 不是领导人查询自己创建的联系人，是否执行
			boolean isSelect = true;
			ContactSearch search = new ContactSearch();
			search.setAccountId(UserState.getLoginId());// 当前登录人
			search.setContactRole("0");
			search.setOrderField(null);
			search.setOrderType("");
			search.setIsqq(false);
			search.setIsemail(false);
			search.setIsphone(false);
			search.setIswechat(false);
			search.setCustomerType("-1");
			search.setContent("");
			int loginId = UserState.getLoginId();
			List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id
			List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
			// 加载列表显示
			List<String> accountIdList = new ArrayList<String>();
			if (accList != null) {
				AccountOfDept first = accList.get(0);
				// 判断是否是领导人
				if (first.isMySelf() && first.getIsDeptManager() == 0) {
					// 判断是否是第一负责人
					if (this.customerServiceImpl.checkIsOwn(customerId)) {
						for (AccountOfDept accountOfDept : accList) {
							accountIdList.add(accountOfDept.getId() + "");
						}
					} else {
						isSelect = false;
						accListNew.add(first);
						accountIdList.add(first.getId() + "");
					}
				} else {
					for (AccountOfDept accountOfDept : accList) {
						accountIdList.add(accountOfDept.getId() + "");
					}
					accListNew = accList;
				}
			}
			search.setPrincipal(accountIdList);
			List<Contact> contacts = new ArrayList<Contact>();
			if (isSelect) {
				search.setCustomerId(customerId);
				contacts = contactService.selectContactByLoginId(search);
			} else {
				ContactSearchByCusId cusList = new ContactSearchByCusId();
				cusList.setCusterId(customerId);
				cusList.setAccountId(loginId);
				contacts = contactService.getConByCusId(cusList);
			}
			
			
			mav.addObject("contacts", contacts);
			mav.setViewName("customer/contact_list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	//获取跟踪记录
	@RequestMapping(value = "cusRecord/getRecordList", method = RequestMethod.POST)
	public ModelAndView recordList(Integer customerId,Integer accountId, HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		
		
		return mav;
	}
	
	@RequestMapping(value = "cusRecord/refreshCusInfo", method = RequestMethod.POST)
	public ModelAndView refreshCusInfo(CustomerShareDTO dto, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Integer customerId = dto.getCustomerId();

		// 根据客户ID查询客户的共享信息
		try {
			CustomerTransDTO customerInfo = this.customerServiceImpl.findCustomerInfoById(customerId);
			mav.addObject("customerInfo", customerInfo);
			mav.setViewName("customer/cusinfo_list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	// 增加联系人
	@RequestMapping(path = "cusRecord/addContact", method = RequestMethod.GET)
	public ModelAndView addContact(Customer vo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dictLxr", dictService.getDictByType(33));
		String customerName = vo.getName();
		Integer customerId = vo.getId();
		mav.setViewName("contact/add_for_share");
		mav.addObject("customerName", customerName);
		mav.addObject("customerId", customerId);

		return mav;
	}

	// 上传名片
	@RequestMapping(path = "cusRecord/uploadCard", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadCard(@RequestParam(value = "uploadVistCard") MultipartFile uploadVistCard,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (uploadVistCard != null) {
			// 获取保存的路径，
			String realPath = request.getSession().getServletContext().getRealPath("/upload/card");
			if (uploadVistCard.isEmpty()) {
				// 未选择文件
				resMap.put("status", "empty");
			} else {
				// 文件原名称
				UUID uuid = UUID.randomUUID();
				String fileName = uuid.toString().toUpperCase();
				String extension = uploadVistCard.getOriginalFilename()
						.substring(uploadVistCard.getOriginalFilename().lastIndexOf('.'));
				String allName = fileName + extension;
				try {
					// 这里使用Apache的FileUtils方法来进行保存
					FileUtils.copyInputStreamToFile(uploadVistCard.getInputStream(), new File(realPath, allName));
					resMap.put("status", "ok");
					resMap.put("path", "../upload/card/" + allName);
				} catch (IOException e) {
					System.out.println("文件上传失败");
					resMap.put("status", "no");
					e.printStackTrace();
				}
			}

		}
		return resMap;
	}
	
	
	
	// 修改客户信息
	@RequestMapping(path = "cusRecord/updateCusInfo", method = RequestMethod.GET)
	public String edit(@RequestParam int id, Model model) throws Exception {
		ServiceResult<Customer> result = customerServiceImpl.getModelById(id);
		if (!result.isSuccess()) {
			model.addAttribute("msg", result.getMsg());
			return "error";
		}

		Customer customer = result.getData();

		List<SysDict> xsztList = this.sysDictServiceImpl.findAllByPid(36);// 销售状态
		List<SysDict> khlxList = this.sysDictServiceImpl.findAllByPid(37);// 客户类型
		List<SysDict> khkyList = this.sysDictServiceImpl.findAllByPid(67);// 客户来源
		List<SysDict> khcglList = this.sysDictServiceImpl.findAllByPid(83);// 客户成功率

		List<Address> list = this.addressServiceImpl.listAllProvs();// 地区 省
		model.addAttribute("listProvs", list);
		List<Address> listCity = this.addressServiceImpl.listCityDicByProv(customer.getProvince());// 地区
																									// 市
		model.addAttribute("listCity", listCity);
		List<Address> listCountry = this.addressServiceImpl.listCityDicByProv(customer.getCity());// 地区
																									// 县
		model.addAttribute("listCountry", listCountry);

		model.addAttribute("model", customer);
		model.addAttribute("xszt", xsztList);
		model.addAttribute("khlx", khlxList);
		model.addAttribute("khly", khkyList);
		model.addAttribute("khcgl", khcglList);
		
		List<String> types = new ArrayList<String>();
		String type = customer.getType();
		
			if (type != null&&type.contains(",")) {
				String[] ts = customer.getType().split(",");
				types = Arrays.asList(ts);
			} else {
				types.add(type);
			}
			model.addAttribute("types", types);
	
		List<String> sources = new ArrayList<String>();
		String source = customer.getSource();
		if (source!=null&&source.contains(",")) {
			String[] sors = customer.getSource().split(",");
			sources = Arrays.asList(sors);
		} else {
			sources.add(source);
		}
		model.addAttribute("sources", sources);

		return "customer/edit_forshare";
	}
	
	@RequiresAuthentication
	@RequiresPermissions(value = "cusRecord_index")
	@RequestMapping(path = "cusRecord/index", method = RequestMethod.GET)
	public String index(Model model){
		
		int loginId = UserState.getLoginId();
		
		CustomerRecordSearchForm search = new CustomerRecordSearchForm();
		String initDate = DateFormat.getDateInstance().format(new Date());
		String endDate = DateFormat.getDateInstance().format(addDateOneDay(new Date(),1));
		search.setStartTime(initDate);
		search.setEndTime(endDate);
		
		List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id获取部门的所有成员
		List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
		String accIds = "";
		if (accList != null) {
			AccountOfDept first = accList.get(0);
			if (first.isMySelf() && first.getIsDeptManager() == 0) {
				accIds = String.valueOf(first.getId());
				accListNew.add(first);
			} else {
				for (AccountOfDept item : accList) {
					accIds += item.getId() + ",";
				}
				if (accIds != "")
					accIds = accIds.substring(0, accIds.length() - 1);
				accListNew = accList;
			}
		}
		search.setIds(accIds);
		search.setAccountId(-1);
		
		Page<CustomerRecordLogsDTO> result = customerRecordServiceImpl.findAllCustomerRecordLogsBy(search);
		
		model.addAttribute("pager", new PageInfo<>(result));
		model.addAttribute("model", result);
		model.addAttribute("accList", accListNew);
		model.addAttribute("search", search);
		model.addAttribute("initDate", initDate);
		return "customerRecord/index";
	}
	
	@RequiresAuthentication
	@RequiresPermissions(value = "cusRecord_index")
	@RequestMapping(path = "cusRecord/index", method = RequestMethod.POST)
	public String index(CustomerRecordSearchForm search, Model model){
		
		search.setEndTime(search.getEndTime() + " 23:59:59");
		
		if(search.getAccountId() == -1){
			
			int loginId = UserState.getLoginId();
			List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id获取部门的所有成员
			List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
			String accIds = "";
			if (accList != null) {
				AccountOfDept first = accList.get(0);
				if (first.isMySelf() && first.getIsDeptManager() == 0) {
					accIds = String.valueOf(first.getId());
					accListNew.add(first);
				} else {
					for (AccountOfDept item : accList) {
						accIds += item.getId() + ",";
					}
					if (accIds != "")
						accIds = accIds.substring(0, accIds.length() - 1);
					accListNew = accList;
				}
			}
			search.setIds(accIds);	
		}
		
		Page<CustomerRecordLogsDTO> result = customerRecordServiceImpl.findAllCustomerRecordLogsBy(search);
		
		model.addAttribute("pager", new PageInfo<>(result));
		model.addAttribute("model", result);
		model.addAttribute("search", search);
		return "customerRecord/_list";
	}
	
	
	private static Date addDateOneDay(Date date,int day) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, day); //日期加1天
        date = c.getTime();
        return date;
	}

	

}
