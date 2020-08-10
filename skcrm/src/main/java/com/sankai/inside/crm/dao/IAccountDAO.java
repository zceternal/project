package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.AccountHaveCustomer;
import com.sankai.inside.crm.entity.AccountOfDept;
import com.sankai.inside.crm.entity.AccountPwd;
import org.apache.ibatis.annotations.Param;

public interface IAccountDAO {

	/**
	 * 查询全部用户信息
	 * 
	 * @param val
	 * @return
	 */
	public List<Account> selectBy(String val);

	/**
	 * 获取所有用户（id,name）
	 * 
	 * @return
	 */
	public List<Account> selectIdAndNameAll(int ignoreId);

	/**
	 * 根据当前id 查询本部门内的所有用户信息
	 * 
	 * @param state
	 *            1:全部、0：正常、-1：删除
	 * @param val
	 * @param id
	 * @return
	 */
	public List<Account> selectDeptAllBy(String val, Integer id, Integer state);

	/**
	 * 根据部门和所有子部门 查询所有用户信息
	 * 
	 * @param val
	 * @param deptIds
	 * @return
	 */
	public List<Account> selectDeptAllByDeptIds(String val, String deptIds, int loginId, int deptId, boolean isManage,Integer state);

	public Account selectAccountByLoginName(String val);

	/**
	 * 真删除
	 * @param id
	 * @return
	 */
	public int delete(int id);
	/**
	 * 假删除
	 * @param id
	 * @return
	 */
	public int updateState(int id);

	public void insert(Account account);

	public Boolean existsByLoginName(String val);

	public Boolean existsByNumber(String val);

	public boolean existsLeader(int deptId);

	public Account selectEasyById(int id);
	/**
	 * 根据用户id获取部门领导人信息
	 * @param id
	 * @return
	 */
	public Account getPrincipalByaccId(int id);
	/**
	 * 根据用户id获取部门领导人信息
	 * 如果删除的是部门领导，则需要找当前人所在部门的父级部门的领导人
	 * @param id
	 * @return
	 */
	public Account getPrincipalByaccIdForPid(int id);
	

	public void update(Account account);

	/*
	 * 查询用户信息 用于主页面的个人
	 */
	public Account selectAccountInfo(int loginId);

	/*
	 * 修改用户信息 用于主页面的个人
	 */
	public void updateAccountInfo(Account account);

	/*
	 * 删除的用户恢复状态
	 */
	public void recoverAccountState(int id);

	/*
	 * 查询用户原密码 用于主页面的修改密码
	 */
	public String selectAccountPwd(Integer loginId);

	/*
	 * 修改用户原密码 用于主页面的修改密码
	 */
	public int updateAccountPwd(AccountPwd model);

	public Account selectNameAndStateById(int id);

	// 重置密码
	public Boolean updatePwd(int id);

	public String getLoginNameX(Integer id) throws Exception;

	public int getDeptIdById(int id);

	public List<String> findAuthById(int id);

	/**
	 * 根据用户id 获取该用户所在部门的所有用户
	 * 
	 * @param accId
	 * @return
	 */
	public List<AccountOfDept> selectAccOfDeptByAccId(int accId);

	/**
	 * 根据所有部门id 获取所在部门的所有用户
	 * 
	 * @param accId
	 *            当前客户id
	 * @param deptIds
	 *            所在部门和子部门
	 * @return
	 */
	public List<AccountOfDept> selectDeptAllAccOfDeptByDeptIds(int accId, String deptIds);

	/**
	 * 根据用户id 判断是否是部门领导人
	 * 
	 * @param id
	 * @return
	 */
	public Integer selectLeaderById(int id);
	
	/**
	 * 用户拥有客户数量
	 * 
	 * @param search
	 * @return
	 */
	public List<AccountHaveCustomer> getAccountHaveCustomer(String deptIds);
	
	/**
	 * 查询销售负责人
	 * @param account
	 * @return
	 */
	public List<Account> selectaAllowByName(Account account);

	String getNames(@Param("ids") String ids);

}
