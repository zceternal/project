package com.sankai.inside.crm.dao;

import java.util.Date;
import java.util.List;

import com.sankai.inside.crm.entity.CustomerLog;
import com.sankai.inside.crm.entity.CustomerLogList;
import com.sankai.inside.crm.entity.CustomerRemindAdd;
import com.sankai.inside.crm.entity.CustomerRemindDTO;
import com.sankai.inside.crm.entity.CustomerRemindEdit;
import com.sankai.inside.crm.entity.CustomerRemindList;
import com.sankai.inside.crm.entity.CustomerRemindSendEmail;

public interface ICustomerRemindDAO {
	
	/**
	 * 根据用户id 获取所有提醒客信息
	 * 注：accountId=null 则为查询全部提醒客信息
	 * @param accountId
	 * @return
	 */
	public List<CustomerRemindList> selectList(Integer accountId) ;
	/**
	 * 客户提醒——发送Email
	 * @param remindDate 提醒日期【格式：yyyy-MM-dd】
	 * @return
	 */
	public List<CustomerRemindSendEmail> selectListByRemindTime(String remindDate) ;
	
	public CustomerRemindDTO selectById(Integer remindId) ;

	public Integer insert(CustomerRemindDTO model);
	
	public int update(CustomerRemindEdit model);
	/**
	 * 修改state状态
	 * @param remindId
	 * @param state
	 */
	public int updateState(Integer remindId,int state);

}
