package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.ContactShare;
import com.sankai.inside.crm.entity.ContactShareExistsCheck;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.UpdateCustomerShare;

public interface IContactShareDAO {

	/**
	 * 插入分享联系人表
	 * 
	 * @param concat实体
	 */
	public void insert(ContactShare model);
	
	public void update(ContactShare model); 
	
	public void delete(int contactId,int accountId);
	/**
	 * 查看是否共享过
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer checkExists(ContactShareExistsCheck check);
	
	/**
	 * 保存联系人共享到表
	 * @return
	 */
	public Integer insertContactShare(List<ContactShare> list);
	/**
	 * 根据用户Id，获取联系人分享表的id集合
	 * @param accId
	 * @return
	 */
	public List<Integer> selectIdsByAccId(Integer accId);
	/**
	 * 根据Id集合，修改用户id和允许人id
	 * @param dto.ids
	 * @return
	 */
	public int updateBatchByIds(ContactShare dto);
	/**
	 * 批量修改联系人状态：状态:0正常；1公海
	 * @param dto
	 * @return
	 */
	public Integer injectHighSeasByCusId(ContactShare dto);
	/**
	 * 客户转移，将客户关联的联系人转移给允许人
	 * @param dto
	 * @return
	 */
	public Integer updateTran(ContactShare dto);
	
}
