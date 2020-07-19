package com.sankai.inside.crm.dao;

import java.util.List;
import java.util.Map;

import com.sankai.inside.crm.entity.AutocomplateEntity;
import com.sankai.inside.crm.entity.Contact;
import com.sankai.inside.crm.entity.ContactAutocomplate;
import com.sankai.inside.crm.entity.ContactSearch;
import com.sankai.inside.crm.entity.ContactSearchByCusId;

public interface IContactDAO {

	/**
	 * 联系人列表
	 * 
	 * @param val
	 *            查询条件
	 * @return 联系人集合
	 */
	public List<Contact> list(ContactSearch search);
	
	/**
	 * 联系人列表  自动填充
	 * 李肖
	 * @param val
	 *            查询条件
	 * @return 联系人集合
	 */
	public List<ContactAutocomplate> listAutocomplate(AutocomplateEntity model);
	
	/**
	 * 客户详情 联系人列表
	 * @param search
	 * @return
	 */
	public List<Contact> showList(ContactSearch search);
/**
 * 双击客户，根据单独的客户id查询联系人
 * @param search
 * @return
 */
	public List<Contact> getConByCusId(ContactSearchByCusId search); 
	/**
	 * 根据id回去联系人实体
	 * 
	 * @param id
	 * @return
	 */
	public Contact selectById(int id);
	public Contact selectByLoginId(int id);
	
	/**
	 * 根据登录id获取联系人集合（不是部门领导人-获取客户-联系人）
	 * @param loginId
	 * @return
	 */
	public List<Contact> getConByLoginId(ContactSearch search);
	
	/**
	 * 插入联系人
	 * 
	 * @param concat实体
	 */
	public Integer insert(Contact concat);
	/**
	 * 插入联系人验证是否存在
	 * 
	 * @param concat实体
	 */
	public Contact insertExit(Contact concat);

	/**
	 * 更新数据
	 * 
	 * @param concat实体
	 */
	public Integer update(Contact concat);

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @return
	 */
	public int delete(int id);
	
	/**
	 * 置顶操作
	 * @param id
	 * @return
	 */
	public Integer topOperate(Map<String,Object> map);
	/**
	 * 获取最大的order
	 * @return
	 */
	public Integer getMaxOrder();
	/**
	 * 大于要修改的sort 值，统一减去1
	 * @return
	 */
	public Integer minusOperate(int sort);

	
	public Contact selectTopContact(int customerId);
	
	/**
	 * 修改联系人的第一负责人 
	 * @param customerId 
	 * @param newAccountId
	 * @return
	 */
	public Integer UpdateFirstMan(Integer customerId,Integer newAccountId);
	/**
	 * 根据客户id 批量删除共享者的信息
	 * @param customerId
	 * @return
	 */
	public Integer DeleteBatchShare(Integer customerId);
	
}
