package com.sankai.inside.crm.service;

import java.util.List;

import com.sankai.inside.crm.entity.CustomerContact;
/*import com.sankai.inside.crm.entity.CustomerIsShare;*/
import com.sankai.inside.crm.entity.CustomerShareCusDTO;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.DeptTable;
import com.sankai.inside.crm.entity.UpdateCustomerShare;

public interface ICustomerShareService {
	/**
	 * 根据ID查找要共享的客户信息 
	 * @param cusid
	 * @return
	 * @throws Exception
	 */
	public CustomerShareCusDTO findCusById(Integer cusid) throws Exception;
	/**
	 * 获取页面结构
	 * @return
	 * @throws Exception
	 */
	public List<DeptTable> getDeptTable() throws Exception;
	
	/**
	 * 保存共享到表
	 * @return
	 */
	public Integer insertCustomerShare(List<CustomerShareDTO> list) throws Exception;
	/**
	 * 查看是否共享过
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean checkExists(Integer allowAccountId,Integer customerId) throws Exception;
	
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
	 * 根据用户Id，获取客户分享表的id集合
	 * @param accId
	 * @return
	 */
	public List<Integer> getIdsByAccId(Integer accId);
	
	/**
	 * 根据Id集合，修改用户id和允许人id
	 * @param dto
	 * @return
	 */
	public int updateBatchByIds(UpdateCustomerShare dto);
}
