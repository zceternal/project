package com.sankai.inside.crm.service;

import java.util.List;

import org.apache.ibatis.javassist.runtime.Inner;

import com.github.pagehelper.Page;
import com.sankai.inside.crm.entity.Contact;
import com.sankai.inside.crm.entity.Customer;
import com.sankai.inside.crm.entity.CustomerAutocomplate;
import com.sankai.inside.crm.entity.CustomerList;
import com.sankai.inside.crm.entity.CustomerListSearch;
import com.sankai.inside.crm.entity.CustomerShare;
import com.sankai.inside.crm.entity.CustomerShareTransDTO;
import com.sankai.inside.crm.entity.CustomerTransDTO;
import com.sankai.inside.crm.entity.CustomerTransfer;
import com.sankai.inside.crm.entity.HomeCount;
import com.sankai.inside.crm.entity.UpdateCustomerStatusDTO;

import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;


public interface ICustomerService {
	/**
	 * 根据ID查询客户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CustomerTransDTO findCustomerInfoById(Integer id)  throws Exception ;
	/**
	 * 根据客户ID查询客户的共享信息
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<CustomerShareTransDTO> findAllows(Integer customerId) throws Exception  ;
	
	/**
	 * 查找销售第一负责人
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public CustomerShareTransDTO findFirstAllow(Integer customerId) throws Exception  ;
	
	/**
	 * 查询客户联系人
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<Contact> findContactInfoByCustoemrId(Integer customerId) throws Exception  ;
	
	/**
	 * 查询客户管理列表
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  ServiceResult<Page<CustomerList>> getList(CustomerListSearch val,int page,int pageSize) ;
	/**
	 * 根据条件导出客户信息（）
	 * @param val
	 * @return
	 */
	public  ServiceResult<List<CustomerList>> getListForExcel(CustomerListSearch val) ;
	
	/**
	 * 查询客户管理列表  自动填充
	 * 李肖
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  List<CustomerAutocomplate> getListAutocomplate(String value,String type) ;
	
	/**
	 * 根据id获取客户信息
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  ServiceResult<Customer> getModelById(int id) ;
	
	/**
	 * 删除客户信息
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public ServiceResultBool remove(int id) ;
	
	/**
	 * 新增客户信息
	 * @param Customer
	 * @return
	 * @throws Exception
	 */
	public ServiceResultBool addCustomer(Customer model);
	
	/**
	 * 新增客户信息
	 * @param Customer
	 * @return
	 * @throws Exception
	 */
	public ServiceResult addCustomerImport(Customer model);
	
	
	/**
	 * 新增客户信息
	 * @param Customer
	 * @return
	 * @throws Exception
	 */
	public Integer getCustomerId(String customerName);

	
	/**
	 * 修改客户信息
	 * @param Customer
	 * @return
	 * @throws Exception
	 */
	public ServiceResultBool saveCustomer(Customer model);
	/**
	 * 修改客户信息 - 简单版
	 * @param model
	 * @return
	 */
	public ServiceResultBool saveCustomerSim(Customer model);
	
	/**
	 * 修改客户状态
	 * @param dto
	 * @return
	 * @throws Excetpion
	 */
	public boolean updateStatus(Integer customerId,Integer status);
	
	/**
	 * 新增客户共享信息
	 * @param CustomerShare
	 * @return
	 * @throws Exception
	 */
	public ServiceResultBool addCustomerShare(CustomerShare model);
	/**
	 * 新增客户共享信息
	 * @param customerId 客户id
	 * @param allowAccountId 相关负责人id
	 * @return
	 * @throws Exception
	 */
	public Integer getMaxOrder(int allowAccountId);
	
	/**
	 * 根据id获取客户分享实体信息
	 * @param shareId
	 * @return
	 */
	public ServiceResult<CustomerShare> getShareById(int shareId);
	
	/**
	 * 根据id获取客户分享实体信息
	 * @param allowAccountId 允许的用户id
	 * @param order 当前客户的order
	 * @return
	 */
	public void editShareOrder(int allowAccountId,int order);
	/**
	 * 
	 * @param shareId
	 * @param order
	 */
	public void upOrder(int shareId);

	public List<Contact> findContactShared(Integer customerId) throws Exception;
	
	public boolean checkIsOwn(Integer customerId) throws Exception;
	/**
	 * 判断客户名称是否存在
	 * @param name 客户名称
	 * @return
	 */
	public ServiceResultBool existsByName(String name);
	/**
	 * 判断客户简称是否存在
	 * @param name 客户简称
	 * @return
	 */
	public ServiceResultBool existsByShortName(String shortName);
	/**
	 * 新增客户转移
	 * @param model
	 */
	public ServiceResultBool addCustomerTransfer(CustomerTransfer model);
	
	/**
	 * 根据客户id和允许人id 获取客户分享实体
	 * @param customerId
	 * @param allowAccountId
	 * @return
	 */
	public CustomerShare getShareBycidAndAllowId(int customerId,int allowAccountId);
	/**
	 * 客户转移  修改客户分享信息
	 * @param model
	 * @return
	 */
	public ServiceResultBool saveShare(CustomerShare model);
	
	/**
	 * 修改客户分享的 删除状态
	 * @param customerId 客户id
	 * @param allowAccountId 分享允许的用户id
	 */
	public Integer saveOrderState(int customerId,int allowAccountId);
	
	/**
	 * 分享客户是否共享 记录报告
	 * @param customerId
	 * @param accountId
	 * @return
	 */
	public Integer getIsShare(Integer customerId,Integer accountId);
	/**
	 * 当前用户的 客户总量
	 * @param accountId
	 * @return
	 */
	public Integer getTotalCustomerByAccountId();
	
	/**
	 * 当前用户的 联系人总量
	 * @param accountId
	 * @return
	 */
	public Integer getTotalContactByAccountId();
	
	/**
	 * N天未跟踪
	 * 注：如果start或end 有一个为null 则为未跟踪客户
	 * @param accountId
	 * @param start 起始天数【大于等于】
	 * @param end 结束天数【小于】
	 * @return
	 */
	public Integer getTotalTraceBy(Integer start,Integer end);
	
	public HomeCount getHomeCount();
	
	/**
	 * 投入公海功能
	 * lix
	 * 20160621
	 * */
	public ServiceResultBool injectHighSeas(Integer cusId);
	
	/**
	 * 批量公海
	 * @param ids
	 * @return
	 */
	public ServiceResultBool injectHighSeasBatch(List<Integer> ids);
	
	
}
