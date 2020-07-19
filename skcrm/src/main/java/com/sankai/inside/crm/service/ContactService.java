package com.sankai.inside.crm.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.core.utils.Pinyin4jUtil;
import com.sankai.inside.crm.dao.IContactDAO;
import com.sankai.inside.crm.dao.IContactShareDAO;
import com.sankai.inside.crm.dao.ICustomerDAO;
import com.sankai.inside.crm.entity.AutocomplateEntity;
import com.sankai.inside.crm.entity.ConCusterSearch;
import com.sankai.inside.crm.entity.Contact;
import com.sankai.inside.crm.entity.ContactAutocomplate;
import com.sankai.inside.crm.entity.ContactSearch;
import com.sankai.inside.crm.entity.ContactSearchByCusId;
import com.sankai.inside.crm.entity.ContactShare;
import com.sankai.inside.crm.entity.SearchCustomerList;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.ContactForm;

@Service
public class ContactService {

	@Autowired
	private IContactDAO contactDAO;
	@Autowired
	private ICustomerDAO customerDAO;
	@Autowired
	private IContactShareDAO contactShareDAO;
	

	@Autowired
	private ContactShareService contactShareService;

	public ServiceResult<Page<Contact>> list(ContactSearch content, int page, int pageSize) {

		PageHelper.startPage(page, pageSize, true);
		Page<Contact> list = (Page<Contact>) contactDAO.list(content);
		return new ServiceResult<Page<Contact>>(list);
	}
	
	/*
	 * 联系人    自动填充
	 * */
	public List<ContactAutocomplate> listAutocomplate(String value,String type) {
		AutocomplateEntity model=new AutocomplateEntity();
		model.setType(type);
		model.setValue(value);
		List<ContactAutocomplate> list = contactDAO.listAutocomplate(model);
		return list;
	}

	/**
	 * 根据登录id获取当前人创建的客户Id
	 * 
	 * @param id
	 * @return
	 */
	public ServiceResult<Page<Contact>> getConByLoginId(ContactSearch content, int page, int pageSize) {
		PageHelper.startPage(page, pageSize, true);
		Page<Contact> list = (Page<Contact>) contactDAO.getConByLoginId(content);
		return new ServiceResult<Page<Contact>>(list);
	}

	/**
	 * 根据登录id获取联系人--双击客户列表
	 * 
	 * @param content
	 * @return
	 */
	public List<Contact> selectContactByLoginId(ContactSearch content) {
		return contactDAO.showList(content);
	}

	public List<Contact> getConByCusId(ContactSearchByCusId search) {
		return contactDAO.getConByCusId(search);
	}

	// 获取客户列表
	public ServiceResult<Page<SearchCustomerList>> searchCustomerList(String content, int page, int pageSize) {
		PageHelper.startPage(page, pageSize, true);

		ConCusterSearch search = new ConCusterSearch();
		search.setContent(content);
		search.setAccountId(UserState.getLoginId());

		Page<SearchCustomerList> list = (Page<SearchCustomerList>) customerDAO.searchCustomerList(search);
		return new ServiceResult<Page<SearchCustomerList>>(list);
	}

	public ServiceResult<Contact> selectById(int id) {

		Contact contact = contactDAO.selectById(id);

		if (contact == null)
			return new ServiceResult<>("数据已不存在，请刷新列表");

		return new ServiceResult<Contact>(contact);

	}

	public ServiceResultBool add(ContactForm model)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Contact contact = new Contact();

        	
		BeanUtils.copyProperties( model,contact);
		contact.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		contact.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));
		contact.setCreateTime(new Date());
		contact.setCreateId(UserState.getLoginId());// 登录人id
		contact.setBirthday(new Date());
		contact.setSort(getMaxOrder() + 1); // 设置置顶最大值
		Contact contactEntity = contactDAO.insertExit(contact);
		if (contactEntity!=null) {
			return new ServiceResultBool("此联系人已存在");
		}
		
		contactDAO.insert(contact);

		// 联系人分享表添加数据
		ContactShare contactShare = new ContactShare();
		contactShare.setAccountId(UserState.getLoginId());// 登录人id
		contactShare.setCustomerId(model.getCustomerId());// 客户Id
		contactShare.setContactId(contact.getId());// 联系人Id
		contactShare.setAllowAccountId(UserState.getLoginId());// 分享某人的Id（默认自己共享给自己）
		contactShareService.add(contactShare);

		return new ServiceResultBool();
	}
	
	/**
	 * 联系人  导入    李肖
	 * 
	 * 
	 * */
	public ServiceResultBool addImport(ContactForm model) {
		Contact contact = new Contact();
		
		Integer loginId = model.getCreateId();
		if(loginId==null)
			loginId = UserState.getLoginId();
		
		BeanUtils.copyProperties(model, contact);
		contact.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		contact.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));
		//contact.setCreateTime(new Date());
		contact.setCreateId(loginId);// 登录人id
		
		contact.setBirthday(new Date());
		contact.setSort(getMaxOrder() + 1); // 设置置顶最大值
		Contact contactEntity = contactDAO.insertExit(contact);
		if (contactEntity!=null) {
			return new ServiceResultBool("此联系人已存在");
		}
		
		contactDAO.insert(contact);

		// 联系人分享表添加数据
		ContactShare contactShare = new ContactShare();
		
			contactShare.setAccountId(loginId);// 登录人id
		contactShare.setCustomerId(model.getCustomerId());// 客户Id
		contactShare.setContactId(contact.getId());// 联系人Id
		contactShare.setAllowAccountId(loginId);// 分享某人的Id（默认自己共享给自己）
		contactShareService.add(contactShare);

		return new ServiceResultBool();
	}
	
	

	public ServiceResultBool update(ContactForm model) {

		Contact contact = new Contact();
		ServiceResultBool result = new ServiceResultBool();
		int customerId = model.getCustomerId();
		
		model.setCustomerId(UserState.getLoginId());
		BeanUtils.copyProperties(model, contact);
		contact.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		contact.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));
		contact.setCustomerId(customerId);
		Contact contactEntity = contactDAO.insertExit(contact);

if (contactEntity!=null&&(!contactEntity.getName().equals(model.getName())&&!contactEntity.getPhone().equals(model.getPhone()))) {
	Contact tempModel = new Contact();
			tempModel.setName(model.getName());
			tempModel.setPhone(model.getPhone());
			tempModel.setId(null);
	Contact a = contactDAO.insertExit(tempModel);
	if (a!=null) {
		return new ServiceResultBool("此联系人已存在");
	}
			
		}
		Integer i = contactDAO.update(contact);
		if (i > 0) {
			
			// 同时修改联系人共享表
			ContactShare contactShare = new ContactShare();
			contactShare.setAccountId(UserState.getLoginId());// 登录人id
			contactShare.setCustomerId(customerId);// 客户Id
			contactShare.setContactId(contact.getId());// 联系人Id
			contactShare.setAllowAccountId(UserState.getLoginId());// 分享某人的Id（默认自己共享给自己）
			contactShareService.update(contactShare);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
			result.setMsg("保存失败");
		}
		return result;
	}

	public ServiceResultBool delete(int id) {
		/*int result = contactDAO.delete(id);
		if (result == 0)
			return new ServiceResultBool("删除失败，请需要删除的数据已不存在");*/
		contactShareService.delete(id,UserState.getLoginId());
		return new ServiceResultBool();
	}

	public ServiceResultBool topOperate(int id, int sort) {
		ServiceResultBool result = new ServiceResultBool();
		int maxSort = getMaxOrder();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sort", maxSort + 1);
		Integer i = contactDAO.topOperate(map);
		if (i > 0) {
			minusOperate(sort);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	public Integer getMaxOrder() {
		Integer i = contactDAO.getMaxOrder() == null ? 0 : contactDAO.getMaxOrder();
		return i;
	}

	public Integer minusOperate(int sort) {
		Integer i = contactDAO.minusOperate(sort);
		return i;
	}
	
	public ServiceResultBool injectHighSeasByCusId(Integer customerId,int state){
		ContactShare dto = new ContactShare();
		dto.setCustomerId(customerId);
		dto.setState(state);
		Integer i = contactShareDAO.injectHighSeasByCusId(dto);
		if(i==null) return new ServiceResultBool("投入公海失败");
		return new ServiceResultBool();
	}
}

