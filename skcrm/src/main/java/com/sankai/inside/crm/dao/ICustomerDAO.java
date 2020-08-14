package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.*;
import org.apache.ibatis.annotations.Param;


public interface ICustomerDAO {
	/**
	 * 根据ID查询客户信息
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CustomerTransDTO findCustomerInfoById(Integer id) throws Exception;

	/**
	 * 根据客户ID查询客户的共享信息
	 *
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<CustomerShareTransDTO> findAllows(Integer customerId) throws Exception;

	/**
	 * 查找销售第一负责人
	 *
	 * @param <CustomerShareTransDTO>
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public <CustomerShareTransDTO> CustomerShareTransDTO findFirstAllow(Integer customerId) throws Exception;

	/**
	 * 查询客户联系人
	 *
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<Contact> findContactInfoByCustoemrId(Integer customerId) throws Exception;

	/**
	 * 查询客户列表
	 *
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public List<CustomerList> selectList(CustomerListSearch search);

	/**
	 * 查询客户列表【导出客户信息】
	 *
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public List<CustomerList> selectListForExcel(CustomerListSearch search);

	/**
	 * 查询客户列表
	 *
	 * 李肖
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public List<CustomerAutocomplate> selectListForAutoComplate(AutocomplateEntity model);

	/**
	 * 搜索客户所有的列表
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<SearchCustomerList> searchCustomerList(ConCusterSearch val);
	/**
	 * 新增客户信息
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertCustomer(Customer model);

	/**
	 * 查询kehuId
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public Integer selectCustomerId(String customerName);

	/**
	 * 修改客户信息
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateCustomer(Customer model);

	/**
	 * 删除客户分享信息
	 *
	 * @param ShareId 客户分享id
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomerShare(int ShareId);

	/**
	 * 删除客户信息
	 *
	 * @param customerId 客户id
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomer(int customerId);

	/**
	 * 根据id获取客户信息
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Customer selectCustomerById(int id);
	/**
	 * 根据创建时间获取联系人信息  用途修改的联系人数据（傻逼写法）
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public Customer selectCustomerByCreateTIme(String createTime);

	/**
	 * 新增客户共享信息
	 *
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public void insertCustomerShare(CustomerShare model);

	/**
	 * 新增客户共享信息
	 *
	 * @param customerId
	 *            客户id
	 * @param allowAccountId
	 *            相关负责人id
	 * @return
	 * @throws Exception
	 */
	public Integer selectMaxOrder( Integer allowAccountId);
	/**
	 * 根据id获取客户分享实体信息
	 * @param shareId
	 * @return
	 */
	public CustomerShare selectShareById(int shareId);

	/**
	 * 根据id获取客户分享实体信息
	 * @param allowAccountId 允许的用户id
	 * @param order 当前客户的order
	 * @return
	 */
	public void updateShareOrder(int allowAccountId,int order);
	/**
	 * 修改实体的  order
	 * @param shareId 当前分享表的id
	 * @param order 修改的order
	 */
	public void updateOrder(int shareId,int order);

	/**
	 * 修改客户分享的 删除状态
	 * @param customerId 客户id
	 * @param allowAccountId 分享允许的用户id
	 */
	public Integer updateOrderState(int customerId,int allowAccountId);


	/**
	 * 查询共享的客户联系人
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<Contact> findContactShared(ContactShareRequestDTO dto) throws Exception;

	/**
	 * 判断客户的负责人是不是这个登录人
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer checkIsOwn(CustomerOwnCheckDTO dto) throws Exception;
	/**
	 * 修改客户状态
	 * @param dto
	 * @return
	 * @throws Excetpion
	 */
	public Integer updateStatus(UpdateCustomerStatusDTO dto);
	/**
	 * 判断客户名称是否存在
	 * @param name 客户名称
	 * @return
	 */
	public boolean existsByName(String name);
	/**
	 * 判断客户简称是否存在
	 * @param name 客户简称
	 * @return
	 */
	public boolean existsByShortName(String shortName);
	/**
	 * 根据客户id和允许人id 获取客户分享实体
	 * @param customerId
	 * @param allowAccountId
	 * @return
	 */
	public CustomerShare selectBycidAndAllowId(int customerId,int allowAccountId);
	/**
	 * 客户转移  修改客户分享信息
	 * @param model
	 * @return
	 */
	public int updateShare(CustomerShare model);

	/**
	 * 根据客户分享id 获取客户信息
	 * @param shareId
	 * @return
	 */
	public Customer selectByShareId(Integer shareId);
	/**
	 * 当前用户的 客户总量
	 * @param accountId
	 * @return
	 */
	public Integer selectTotalCustomerByAccountId(Integer accountId);

	/**
	 * 当前用户的 联系人总量
	 * @param accountId
	 * @return
	 */
	public Integer selectTotalContactByAccountId(Integer accountId);

	/**
	 * N天未跟踪
	 * 注：如果start或end 有一个为null 则为未跟踪客户
	 * @param accountId
	 * @param start 起始天数【大于等于】
	 * @param end 结束天数【小于】
	 * @return
	 */
	public Integer selectTotalTraceBy(Integer accountId,Integer start,Integer end);

	/**
	 * 投入公海功能
	 * lix
	 * 20160621
	 * */
	public Integer injectHighSeas(Integer cusId);
	/**
	 * 批量公海
	 * @param ids
	 * @return
	 */
	public Integer injectHighSeasBatch(List<Integer> ids);
	/**
	 * 根据用户Id,获取不同客户状态的数量
	 * @param accId
	 * @return
	 */
	public List<CustomerStatus> selectStatusNo(Integer accId);

	/**
	 * 客户转移 - 修改创建时间
	 * @param model
	 * @return
	 */
	public Integer updateCustomerSim(Customer model);

	/**
	 * 获取客户人际关系
	 * @param customerId
	 * @return
	 */
	public SysCustomerRelations getCustomerRelations(@Param("customerId") int customerId);

	/**
	 * 新增客户人际关系
	 * @param model
	 * @return
	 */
	public int insertCustomerRelations(SysCustomerRelations model);

	/**
	 * 修改客户信息
	 * @param model
	 * @return
	 */
	int updateByPrimaryKeySelective(Customer model);
}
