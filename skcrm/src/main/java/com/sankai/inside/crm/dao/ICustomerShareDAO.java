package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.CustomerContact;
import com.sankai.inside.crm.entity.CustomerShareAccDTO;
import com.sankai.inside.crm.entity.CustomerShareCusDTO;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.CustomerShareExistsCheckDTO;
import com.sankai.inside.crm.entity.DeptDTO;
import com.sankai.inside.crm.entity.UpdateCustomerShare;

public interface ICustomerShareDAO {
	/**
	 * 根据ID查找要共享的客户信息 
	 * @param cusid
	 * @return
	 * @throws Exception
	 */
	public CustomerShareCusDTO findCusById(Integer cusid) throws Exception;
	
	
	
	public List<DeptDTO> getDeptsByPid(Integer deptId) throws Exception;
	public List<CustomerShareAccDTO> getAccountsByDeptId(List<String> deptIds) throws Exception;
	
	/**
	 * 取得最大order
	 * @param allowAccountId
	 * @return
	 * @throws Exception
	 */
	public Integer getMaxOrderByAllowId(Integer allowAccountId) throws Exception;
	/**
	 * 新增批量客户共享到表
	 * @return
	 */
	public Integer insertCustomerShare(List<CustomerShareDTO> list) throws Exception;
	/**
	 * 新增客户共享
	 */
	public Integer insertShare(CustomerShareDTO list) throws Exception;
	
	
	/**
	 * 查看是否共享过
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer checkExists(CustomerShareExistsCheckDTO dto) throws Exception;
	
	
	/**
	 * 根据客户的id获取联系人集合
	 * @param id
	 * @return
	 */
	public List<CustomerContact> contactByCuId(Integer customerId);
	
	/**
	 * 修改是否共享销售跟踪记录
	 * @param share
	 * @return
	 */
	public boolean updateCusIsShare(UpdateCustomerShare share);
	/**
	 * 根据客户id 删除所有客户共享信息【作用于：销售负责人删除客户信息】
	 * @param customerId
	 * @return
	 */
	public Integer deleteShareByCustomerId(Integer customerId);
	
	/**
	 * 根据客户分享id 判断是否是客户的销售负责人
	 * @param shareId
	 * @return
	 */
	public Integer selectOwn(int shareId);
	
	/**
	 * 根据当前用户id 判断是否是客户的销售负责人
	 * @param accountId
	 * @return
	 */
	public Integer selectOwnByAccountId(int accountId);
	
	/**
	 * 分享客户是否共享 记录报告
	 * @param customerId
	 * @param accountId
	 * @return
	 */
	public Integer selectIsShare(Integer customerId,Integer accountId);
	
	/**
	 * 根据用户Id，获取客户分享表的id集合
	 * @param accId
	 * @return
	 */
	public List<Integer> selectIdsByAccId(Integer accId);
	/**
	 * 根据Id集合，修改用户id和允许人id
	 * @param dto.ids
	 * @return
	 */
	public int updateBatchByIds(UpdateCustomerShare dto);
	
} 
