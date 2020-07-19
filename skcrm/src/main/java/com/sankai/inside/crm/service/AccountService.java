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
import java.util.stream.Collectors;

import javax.annotation.Resource;

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
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.core.utils.MD5Util;
import com.sankai.inside.crm.core.utils.MathUtil;
import com.sankai.inside.crm.core.utils.Pinyin4jUtil;
import com.sankai.inside.crm.core.utils.TransformResult;
import com.sankai.inside.crm.dao.IAccountDAO;
import com.sankai.inside.crm.dao.IAuthorityDAO;
import com.sankai.inside.crm.dao.IDepartmentDAO;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.AccountHaveCustomer;
import com.sankai.inside.crm.entity.AccountOfDept;
import com.sankai.inside.crm.entity.AccountPwd;
import com.sankai.inside.crm.entity.Authority;
import com.sankai.inside.crm.entity.ContactShare;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.Module;
import com.sankai.inside.crm.entity.ModuleItem;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.entity.UpdateCustomerShare;
/*import com.sankai.inside.crm.web.core.SuperAccount;*/
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.AccountForm;
import com.sankai.inside.crm.web.model.AccountPwdForm;

@Service
public class AccountService {

	public static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private IAccountDAO accountDAO;

	@Autowired
	private IAuthorityDAO authorityDAO;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private IDepartmentDAO deptDAO;
	
	@Resource
	private ICustomerShareService customerShareService;// 客户分享
	@Autowired
	private ContactShareService contactShareService;//联系人分享

	public ServiceResult<Page<Account>> getAccountsByPage(String content, int page, int pageSize, Integer getState) {

		Integer loginId = UserState.getLoginId();

		if (loginId <= 0) // 总管理员
		{
			PageHelper.startPage(page, pageSize, true);
			Page<Account> list = (Page<Account>) accountDAO.selectDeptAllBy(content, UserState.getLoginId(), getState);
			return new ServiceResult<Page<Account>>(list);
		}
		boolean isManage = accountDAO.selectLeaderById(loginId) == 1;
		List<Department> depts = deptDAO.selectAll();
		int deptId = accountDAO.getDeptIdById(loginId);// 根据当前登录Id获取部门Id
		String deptIds = getDeptChilds(depts, deptId);// 递归获取所有子部门id
		if (!StringUtils.isEmpty(deptIds))
			deptIds = deptIds.substring(0, deptIds.length() - 1);
		else
			deptIds = "-1";

		PageHelper.startPage(page, pageSize, true);
		Page<Account> list = (Page<Account>) accountDAO.selectDeptAllByDeptIds(content, deptIds, loginId, deptId,
				isManage, getState);
		return new ServiceResult<Page<Account>>(list);
	}

	public String getDeptChilds(List<Department> source, int deptId) {
		String result = "";
		for (Department item : source) {
			if (item.getPid() == deptId) {
				int id = item.getId();
				result += id + ",";
				result += getDeptChilds(source, id);
			}
		}
		return result;
	}

	public ServiceResult<Account> getEasyAccount(int id) {

		Account account = new Account();
		/*
		 * if(id<=0){ account.setName(SuperAccount.getName());
		 * account.setSex(Integer.parseInt(SuperAccount.getSex()));
		 * account.setPhone("17000000000");
		 * account.setEmail("lenghonghao@sankai.com"); }else{
		 */
		account = accountDAO.selectEasyById(id);
		/* } */

		if (account == null)
			return new ServiceResult<>("数据已不存在，请刷新列表");

		return new ServiceResult<Account>(account);

	}

	/**
	 * 
	 * @param id
	 * @param state -1删除；其他修改状态
	 * @return
	 */
	public ServiceResultBool remove(int id,int state) {
		int result = 0;
		//1 删除账号信息
		if(state==-1)
		{
			result = accountDAO.delete(id);//真删除
		}else{
			//2获取当前人的部门领导
			Account account = accountDAO.getPrincipalByaccId(id);
			if(account!=null){
				Integer leaderId = account.getId();
				if(leaderId==id){//如果删除的是部门领导，则需要找当前人所在部门的父级部门的领导人
					account = accountDAO.getPrincipalByaccIdForPid(id);
					if(account!=null)leaderId = account.getId();
				}
				//3将全部客户转移给部门领导【如果不存在则不出来，由管理员手动处理】
				List<Integer> shareIdList = customerShareService.getIdsByAccId(id);
				if(shareIdList!=null){
					UpdateCustomerShare dto = new UpdateCustomerShare();
					dto.setIds(shareIdList);
					dto.setLeaderId(leaderId);
					customerShareService.updateBatchByIds(dto);
				}
				//4将全部联系人转移给部门领导【如果不存在则不出来，由管理员手动处理】
				shareIdList = contactShareService.getIdsByAccId(id);
				if(shareIdList!=null){
					ContactShare dto = new ContactShare();
					dto.setIds(shareIdList);
					dto.setLeaderId(leaderId);
					contactShareService.updateBatchByIds(dto);
				}
			}
			result = accountDAO.updateState(id);//假删除
		}
		
		if (result == 0)
			return new ServiceResultBool("删除失败，请需要删除的数据已不存在");
		return new ServiceResultBool();
	}

	public int getDeptIdById(int id) {
		return accountDAO.getDeptIdById(id);
	}

	public ServiceResult<Account> login(String loginName, String password) {

		if (!accountDAO.existsByLoginName(loginName)) {

			return new ServiceResult<>("用户名或密码不正确");

		}

		Account account = accountDAO.selectAccountByLoginName(loginName);

		/*
		 * if (!account.getPassword().equals(MD5Util.getMD5String(password))) {
		 */
		if (!account.getPassword().equals(password)) {
			return new ServiceResult<>("用户名或密码不正确");

		}

		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(account.getId() + "", account.getLoginName());
		try {
			subject.login(token);
		} catch (Exception e) {
			return new ServiceResult<>(e.toString());
		}
		ServiceResult<Account> result = new ServiceResult<Account>(account);
		return new ServiceResult<Account>(account);

	}

	public void logout() {

		Subject subject = SecurityUtils.getSubject();
		subject.logout();

	}

	public List<Account> findIdAndNameAll(int ignoreId) {
		return accountDAO.selectIdAndNameAll(ignoreId);
	}

	@SuppressWarnings("rawtypes")
	public ServiceResult add(AccountForm model, int type)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ServiceResult<Account> result = new ServiceResult<Account>();
		if (accountDAO.existsByLoginName(model.getLoginName()))
			return new ServiceResult("用户名已存在");
		if (model.getNumber() != "" && accountDAO.existsByNumber(model.getNumber()))
			return new ServiceResult("工号已存在");
		if (model.getIsDeptManager() == 1 && accountDAO.existsLeader(model.getDeptId()))
			return new ServiceResult("一个部门只能存在一个部门领导");
		Account account = new Account();
		PropertyUtils.copyProperties(account, model);

		account.setCreateId(UserState.getLoginId());
		account.setCreateTime(new Date());
		account.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(account.getName()));
		account.setNamePy(Pinyin4jUtil.getPinYin(account.getName()));
		account.setPassword(MD5Util.getMD5String(model.getNewPassword()));
		account.setType(type);
		account.setAvatar(model.getAvatar());

		accountDAO.insert(account);
		result.setSuccess(true);
		result.setData(account);

		return result;
	}

	@CacheEvict(value = "accountServiceGetNameAndStateById", key = "#model.getId()")
	public ServiceResultBool save(AccountForm model)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Account account = accountDAO.selectAccountInfo(model.getId());
		if (model.getIsDeptManager() == 1 && account.getDeptId() != model.getDeptId()
				&& accountDAO.existsLeader(model.getDeptId()))
			return new ServiceResultBool("一个部门只能存在一个部门领导");

		PropertyUtils.copyProperties(account, model);
		account.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(account.getName()));
		account.setNamePy(Pinyin4jUtil.getPinYin(account.getName()));
		accountDAO.update(account);
		return new ServiceResultBool();
	}

	public ServiceResultBool existsByLoginName(String loginName) {

		if (accountDAO.existsByLoginName(loginName)) {
			return new ServiceResultBool("用户名已存在");
		}
		return new ServiceResultBool();
	}

	public ServiceResultBool existsByNumber(String number) {

		if (accountDAO.existsByNumber(number)) {
			return new ServiceResultBool("工号已存在");
		}
		return new ServiceResultBool();
	}

	public Account getAccountInfo(int loginId) {
		return accountDAO.selectAccountInfo(loginId);
	}

	@CacheEvict(value = "accountServiceGetNameAndStateById", key = "#model.getId()")
	public ServiceResultBool saveAccountInfo(AccountForm model)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Account account = new Account();
		account.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		account.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));

		account.setId(UserState.getLoginId());
		account.setName(model.getName());
		account.setEmail(model.getEmail());
		account.setPhone(model.getPhone());
		account.setSex(model.getSex());
		accountDAO.updateAccountInfo(account);
		return new ServiceResultBool();
	}

	// 删除的用户恢复状态
	public ServiceResultBool recoverAccountState(int id) {
		accountDAO.recoverAccountState(id);
		return new ServiceResultBool();
	}

	@Cacheable(value = "accountServiceGetNameAndStateById")
	public Account getNameAndStateById(int id) {

		return accountDAO.selectNameAndStateById(id);
	}

	public String getLoginNameX(Integer id) throws Exception {
		return this.accountDAO.getLoginNameX(id);
	}

	public int getLoginDept() {
		return this.accountDAO.getDeptIdById(UserState.getLoginId());
	}

	public ServiceResultBool saveAccountPwd(AccountPwdForm model)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		AccountPwd account = new AccountPwd();
		Integer loginId = UserState.getLoginId();
		String entity = accountDAO.selectAccountPwd(loginId);

		if (!MD5Util.getMD5String(model.getOldPwd()).equals(entity)) {
			return new ServiceResultBool(false, "原密码输入错误");
		}

		PropertyUtils.copyProperties(account, model);
		account.setNewPwd(MD5Util.getMD5String(account.getNewPwd()));
		account.setId(loginId);
		int data = accountDAO.updateAccountPwd(account);
		if (data <= 0) {
			return new ServiceResultBool(false, "保存失败");
		}
		return new ServiceResultBool();
	}

	public ServiceResultBool resetPwd(int id)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Boolean result = accountDAO.updatePwd(id);
		if (!result) {
			return new ServiceResultBool(false, "重置密码失败");
		}
		return new ServiceResultBool();
	}

	public Module[] getModuleByAccount(int accountId, Module[] result) throws IOException {

		List<Authority> checkedAuth = authorityDAO.selectByAccount(accountId);

		for (Module module : result) {

			int index = 0;

			for (ModuleItem item : module.getItems()) {

				if (checkedAuth.stream().filter(x -> x.getModuleKey().equals(item.getKey())).count() > 0) {

					item.setChecked(true);
					index++;
				}
			}
			if (module.getItems().size() > 0 && module.getItems().size() == index) {

				module.setChecked(true);

			}
		}
		return result;
	}

	public Module[] filterAuth(List<String> auth) throws IOException {

		List<Module> data = Arrays.asList(moduleService.GetModules());

		Iterator<Module> iter = data.iterator();

		while (iter.hasNext()) {

			Module m = iter.next();

			Iterator<ModuleItem> iters = m.getItems().iterator();

			while (iters.hasNext()) {

				ModuleItem item = iters.next();

				if (!auth.contains(item.getKey())) {
					iters.remove();
				}
			}
		}
		data = data.stream().filter(x -> x.getItems().size() > 0).collect(Collectors.toList());

		return (Module[]) data.toArray(new Module[data.size()]);

	}

	public Module[] getModuleById(int id) throws IOException {
		Module[] result = null;
		if (id <= 0) {
			result = moduleService.GetModules();
		} else {
			List<String> data = getAuthById(id);
			result = filterAuth(data);
		}
		return result;
	}

	@Cacheable(value = "sys-authResourceCache")
	public List<String> getAuthById(int id) throws IOException {
		int deptId = accountDAO.getDeptIdById(id);
		List<String> result = getAuthByAccountId(id);
		List<String> deptAuth = departmentService.getAuthByDeptId(deptId);
		List<String> totalAuth = new ArrayList<String>();

		for (String auth : deptAuth) {
			if (totalAuth.contains(auth))
				continue;
			totalAuth.add(auth);
		}
		for (String persAuth : result) {
			if (totalAuth.contains(persAuth))
				continue;
			totalAuth.add(persAuth);
		}
		return totalAuth;
	}

	@Cacheable(value = "sys-resourceCache")
	public List<String> getAuthByAccountId(int id) throws IOException {
		List<String> result = new ArrayList<>();
		if (id <= 0) {
			List<Module> modules = Arrays.asList(moduleService.GetModules());
			for (Module module : modules) {
				for (ModuleItem item : module.getItems()) {
					result.add(item.getKey());
				}
			}
			return result;
		}
		result = accountDAO.findAuthById(id);
		logger.info("id:" + id + "findAuthByIdCount：" + (result != null ? result.size() : 0));
		return result;
	}

	@CacheEvict(value = "sys-resourceCache", key = "#accountId")
	public ServiceResultBool saveAuth(List<String> authKey, int accountId) {

		List<String> source = this.authorityDAO.selectByAccount(accountId).stream().map(x -> x.getModuleKey())
				.collect(Collectors.toList());

		TransformResult result = MathUtil.Transform(source, authKey);

		if (result.getNewList().size() > 0) {
			List<Authority> insertList = new ArrayList<>();
			for (String val : result.getNewList()) {

				Authority m = new Authority();
				m.setType(2);
				m.setQuoteId(accountId);
				m.setModuleKey(val);
				insertList.add(m);
			}

			this.authorityDAO.insertBatch(insertList);
		}

		if (result.getDeleList().size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("quoteId", accountId);
			map.put("list", result.getDeleList());
			map.put("type", 2);
			this.authorityDAO.deleteBatch(map);
		}
		return new ServiceResultBool();
	}

	/**
	 * 根据用户id 获取该用户所在部门的所有用户
	 * 
	 * @param accId
	 * @return
	 */
	public List<AccountOfDept> getAccOfDeptByAccId(int accId) {
		if (accId <= 0)
			return accountDAO.selectAccOfDeptByAccId(accId);
		else
			return selectDeptAllAccOfDeptByDeptIds(accId);
	}

	/**
	 * 根据所有部门id 获取所在部门的所有用户
	 * 
	 * @param deptIds
	 * @return
	 */
	public List<AccountOfDept> selectDeptAllAccOfDeptByDeptIds(Integer accId) {

		if (accId == null)
			accId = 0;
		List<Department> depts = deptDAO.selectAll();
		int deptId = accountDAO.getDeptIdById(UserState.getLoginId());// 根据当前登录Id获取部门Id
		String deptIds = deptId + "," + getDeptChilds(depts, deptId);// 递归获取所有子部门id
		if (!StringUtils.isEmpty(deptIds))
			deptIds = deptIds.substring(0, deptIds.length() - 1);
		return accountDAO.selectDeptAllAccOfDeptByDeptIds(accId, deptIds);
	}

	/**
	 * 根据用户id 判断是否是部门领导人
	 * 
	 * @param id
	 * @return
	 */
	public boolean isLeaderById(int id) {
		if (id <= 0)
			return true;// 超级管理员【可以查看所有销售人员的客户】
		return accountDAO.selectLeaderById(id) == 1;
	}

	public List<AccountHaveCustomer> getAccountHaveCustomer(String deptIds) {
		return accountDAO.getAccountHaveCustomer(deptIds);
	}

}
