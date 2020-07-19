package com.sankai.inside.crm.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sankai.inside.crm.dao.IAccountGroupDao;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.AccountGroupEntity;
import com.sankai.inside.crm.entity.AccountGroupFrom;
import com.sankai.inside.crm.entity.AccountGroupSearch;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.web.core.UserState;

@Service
public class AccountGroupService {

	public static Logger logger = LoggerFactory.getLogger(AccountGroupService.class);

	@Autowired
	private IAccountGroupDao service;
	
	@SuppressWarnings("rawtypes")
	public ServiceResult add(AccountGroupFrom dto) throws Exception
	{
		AccountGroupEntity entity = new AccountGroupEntity();
		ServiceResult<AccountGroupEntity> result = new ServiceResult<AccountGroupEntity>();
		PropertyUtils.copyProperties(entity, dto);
		entity.setCreateName(UserState.getLoginName());
		entity.setCreateTime(null);//默认数据库当前时间
		entity.setLastModifyName(UserState.getLoginName());
		service.insert(entity);
		result.setSuccess(true);
		result.setData(entity);
		return result ;
	}
	
	public List<AccountGroupEntity> getList(AccountGroupSearch search)
	{
		return service.getList(search);
	}
	
	public List<AccountGroupEntity> getListByAccId(Integer accountId)
	{
		return service.getListByAccId(accountId);
	}
	/**
	 * 公海抢占客户 - 需要排除的用户id
	 * @param accountId
	 * @return
	 */
	public String getAccountIdStr (Integer accountId){
		
		///////////////////////所有组
		AccountGroupSearch search = new AccountGroupSearch();
		List<AccountGroupEntity> list1 =  getList(search);//所有组
		if(CollectionUtils.isEmpty(list1))//群组无数据
		{
			return String.valueOf(accountId);//如果没有群组则返回当前登录人id
		}
		String idAll = "";//收集所有组成员id
		for (AccountGroupEntity item : list1) {
			idAll += item.getAccountIdList()+",";
		}
		String[] idAllList = idAll.split(",");
		
		/////////////////////我所在的组
		String idMyGroup = "";//收集我所在的组的所有成员id
		List<AccountGroupEntity> list2 =  getListByAccId(accountId);//我所在的组
		if(!CollectionUtils.isEmpty(list2))
		{
			for (AccountGroupEntity item : list2) {
				idMyGroup += item.getAccountIdList()+",";
			}
			String ids = "";
			if(idAllList.length>0&&StringUtils.isNotEmpty(idMyGroup))
			{
				for (String item : idAllList) {
					if(idMyGroup.contains(item)) continue;//不属于我组的用户id的客户需要排除掉
					ids += item+",";
				}
				return ids+String.valueOf(accountId);
			}
		}
		return idAll+String.valueOf(accountId);
	}
	
	public ServiceResult<AccountGroupEntity> findByPriKey(int id){
		AccountGroupEntity model = service.findByPriKey(id);
		if (model == null)
			return new ServiceResult<>("数据已不存在，请刷新列表");
		return new ServiceResult<AccountGroupEntity>(model);
	}
	
	public ServiceResultBool existsByGroupName(String groupName) {

		if (service.existsByGroupName(groupName)) {
			return new ServiceResultBool("组名已存在");
		}
		return new ServiceResultBool();
	}
	
	public ServiceResultBool deleteByPriKey(int id){
		 int result = service.deleteByPriKey(id);
		 if(result > 0) return new ServiceResultBool();
		 return new ServiceResultBool("操作失败");
	}
	public ServiceResultBool modify(AccountGroupFrom dto) throws Exception
	{
		AccountGroupEntity entity = new AccountGroupEntity();
		PropertyUtils.copyProperties(entity, dto);
		entity.setLastModifyName(UserState.getLoginName());
		int result =  service.update(entity);
		 if(result > 0) return new ServiceResultBool();
		 return new ServiceResultBool("操作失败");
		
	}
	
}
