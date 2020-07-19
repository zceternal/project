package com.sankai.inside.crm.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.sankai.inside.crm.entity.CustomerRecord;
import com.sankai.inside.crm.entity.CustomerRecordDTO;
import com.sankai.inside.crm.entity.CustomerRecordIsShare;
import com.sankai.inside.crm.entity.CustomerRecordLogsDTO;
import com.sankai.inside.crm.entity.CustomerRecordShareDTO;
import com.sankai.inside.crm.entity.LikeItReponseDTO;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.web.model.CustomerRecordSearchForm;

public interface ICustomerRecordService {
	/**
	 * 销售跟踪记录列表
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ServiceResult<Page<CustomerRecordDTO>> findAllByCustomerIdGet(Integer deptLeader,Integer customerId, Integer accountId,Integer typeId, Integer page,
			int pageSize) throws Exception;
	public ServiceResult<Page<CustomerRecordDTO>> findAllByCustomerIdGet(Integer deptLeader,Integer customerId, Integer accountId,Integer typeId, Integer page,
			int pageSize,Date startTime,Date endTime) throws Exception;
	public ServiceResult<Page<CustomerRecordDTO>> findAllByCustomerId(Integer deptLeader,Integer customerId, Integer accountId,Integer typeId, Integer page,
			int pageSize) throws Exception;

	/**
	 * 点赞
	 * 
	 * @param loginId
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	public LikeItReponseDTO like(Integer loginId, Integer recordId);

	/**
	 * 添加纪录
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean insertRecord(CustomerRecord dto) throws Exception;

	/**
	 * 获取所有的发布记录人
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<CustomerRecordDTO> findAllpublishers(Integer customerId,Integer state) throws Exception;

	/**
	 * 根据客户Id 和 分享给默认Id 判断销售跟踪记录是否共享
	 * 
	 * @param share
	 * @return
	 */
	public CustomerRecordShareDTO getIsShare(CustomerRecordIsShare share);
	
	public Page<CustomerRecordLogsDTO> findAllCustomerRecordLogsBy(CustomerRecordSearchForm dto);
}
