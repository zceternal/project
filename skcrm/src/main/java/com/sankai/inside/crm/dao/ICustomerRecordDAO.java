package com.sankai.inside.crm.dao;

import java.util.Date;
import java.util.List;

import com.sankai.inside.crm.entity.CustomerRecord;
import com.sankai.inside.crm.entity.CustomerRecordDTO;
import com.sankai.inside.crm.entity.CustomerRecordIsShare;
import com.sankai.inside.crm.entity.CustomerRecordListDTO;
import com.sankai.inside.crm.entity.CustomerRecordLogsDTO;
import com.sankai.inside.crm.entity.CustomerRecordShareDTO;
import com.sankai.inside.crm.entity.LastTimeDTO;
import com.sankai.inside.crm.entity.LikeItDTO;
import com.sankai.inside.crm.web.model.CustomerRecordSearchForm;
import org.apache.ibatis.annotations.Param;

public interface ICustomerRecordDAO {
	/**
	 * 销售跟踪记录列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CustomerRecordDTO> findAllByCustomerId(CustomerRecordListDTO dto) throws Exception;
	/**
	 * 点赞
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer insertRecordPraise(LikeItDTO dto) throws Exception;
	/**
	 * 获取新的点赞数量 
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	public Integer getLikeQty(Integer recordId ) throws Exception;
	/**
	 * 是否已经赞过，赞过为1，否则为0
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer isLiked(LikeItDTO dto ) throws Exception; 
	/**
	 * 获取记录人上一次跟踪时间 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Date getLastTimeByAccountId(LastTimeDTO dto) throws Exception;
	
	/**
	 * 添加纪录
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer insertRecord(CustomerRecord dto);
	/**
	 * 获取所有的发布记录人  
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
 	public List<CustomerRecordDTO> findAllpublishers(CustomerRecordListDTO dto) throws Exception;
	
	/**
	 * 根据客户Id 和 分享给默认Id 判断销售跟踪记录是否共享
	 * @param share
	 * @return
	 */
 	public CustomerRecordShareDTO getIsShare(CustomerRecordIsShare share);
 	
 	/**
 	 * 处理记录查询
 	 * @param dto
 	 * @return
 	 */
	public List<CustomerRecordLogsDTO> findAllCustomerRecordLogsBy(CustomerRecordSearchForm dto);

	/**
	 * 获取最近一条记录
	 * @param customerId
	 * @return
	 */
	CustomerRecord getLastReport(@Param("customerId") int customerId);
	
}
