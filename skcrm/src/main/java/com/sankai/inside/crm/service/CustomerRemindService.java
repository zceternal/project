package com.sankai.inside.crm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.core.utils.MD5Util;
import com.sankai.inside.crm.core.utils.MathUtil;
import com.sankai.inside.crm.core.utils.Pinyin4jUtil;
import com.sankai.inside.crm.core.utils.TransformResult;
import com.sankai.inside.crm.dao.IAccountDAO;
import com.sankai.inside.crm.dao.IAuthorityDAO;
import com.sankai.inside.crm.dao.ICustomerRemindDAO;
import com.sankai.inside.crm.dao.IDepartmentDAO;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.AccountOfDept;
import com.sankai.inside.crm.entity.AccountPwd;
import com.sankai.inside.crm.entity.Authority;
import com.sankai.inside.crm.entity.CustomerRemindAdd;
import com.sankai.inside.crm.entity.CustomerRemindDTO;
import com.sankai.inside.crm.entity.CustomerRemindEdit;
import com.sankai.inside.crm.entity.CustomerRemindList;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.Module;
import com.sankai.inside.crm.entity.ModuleItem;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
/*import com.sankai.inside.crm.web.core.SuperAccount;*/
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.AccountForm;
import com.sankai.inside.crm.web.model.AccountPwdForm;

@Service
public class CustomerRemindService {

	public static Logger logger = LoggerFactory.getLogger(CustomerRemindService.class);

	@Autowired
	private ICustomerRemindDAO customerRemindDAO;
	
	public ServiceResult<Page<CustomerRemindList>> getList(int page, int pageSize) {
		Integer accountId = UserState.getLoginId();
		PageHelper.startPage(page, pageSize, true);
		Page<CustomerRemindList> list = (Page<CustomerRemindList>) customerRemindDAO.selectList(accountId);
		return new ServiceResult<Page<CustomerRemindList>>(list);
		
	}

	public CustomerRemindDTO getById(Integer id) {
		return customerRemindDAO.selectById(id);
	}
	
	public ServiceResultBool add(CustomerRemindAdd add){
		CustomerRemindDTO model = new CustomerRemindDTO();
		model.setCreateId(UserState.getLoginId());
		model.setCreateTime(new Date());
		model.setCustomerId(add.getCustomerId());
		model.setRemindTime(add.getRemindTime());
		model.setState(0);
		model.setType(0);
		model.setRemark(add.getRemark());
		
		 customerRemindDAO.insert(model);
		 model.getId();
		 return new ServiceResultBool();
	}
	
	public ServiceResultBool edit(CustomerRemindEdit model){
		customerRemindDAO.update(model);
		return new ServiceResultBool();
	}
	/**
	 * 修改state状态
	 * @param remindId
	 * @param state
	 */
	public ServiceResultBool remove(Integer remindId){
		if(customerRemindDAO.updateState(remindId, -1)==0)
			return new ServiceResultBool("删除失败，您需要删除的数据已不存在");
		return new ServiceResultBool();
	}
}
