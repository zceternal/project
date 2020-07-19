package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.AccountGroupEntity;
import com.sankai.inside.crm.entity.AccountGroupSearch;
/**
 * 描述：</b>用户群组：用于大客户公海抢占权限<
 * @author：ZZC
 * @version:2016-11-11
 */
public interface IAccountGroupDao {
	
	public int insert(AccountGroupEntity entity);
	
	public int update(AccountGroupEntity entity);
	
	public int deleteByPriKey(int id);
	
	public AccountGroupEntity findByPriKey(int id);
	
	public Boolean existsByGroupName(String groupName);
	
	public List<AccountGroupEntity> getList(AccountGroupSearch search);
	/**
	 * 根据当前登录人，获取群组信息集合
	 * @param accountId 格式：【，id，】
	 * @return
	 */
	public List<AccountGroupEntity> getListByAccId(Integer accountId);
	
}
