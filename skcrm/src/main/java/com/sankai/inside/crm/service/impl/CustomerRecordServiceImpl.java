package com.sankai.inside.crm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.dao.ICustomerRecordDAO;
import com.sankai.inside.crm.dao.ICustomerRecordRevertDAO;
import com.sankai.inside.crm.entity.CustomerRecord;
import com.sankai.inside.crm.entity.CustomerRecordDTO;
import com.sankai.inside.crm.entity.CustomerRecordIsShare;
import com.sankai.inside.crm.entity.CustomerRecordListDTO;
import com.sankai.inside.crm.entity.CustomerRecordLogsDTO;
import com.sankai.inside.crm.entity.CustomerRecordRevert;
import com.sankai.inside.crm.entity.CustomerRecordShareDTO;
import com.sankai.inside.crm.entity.LastTimeDTO;
import com.sankai.inside.crm.entity.LikeItDTO;
import com.sankai.inside.crm.entity.LikeItReponseDTO;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.service.ICustomerRecordService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.CustomerRecordSearchForm;

@Service
public class CustomerRecordServiceImpl implements ICustomerRecordService {
	public static Logger logger = LoggerFactory.getLogger(CustomerRecordServiceImpl.class);
	@Resource
	private ICustomerRecordDAO customerRecordDAO;
	
	@Resource
	private ICustomerRecordRevertDAO customerRecordRevertDAO;

	@Override
	public ServiceResult<Page<CustomerRecordDTO>> findAllByCustomerIdGet(Integer deptLeader,Integer customerId, Integer accountId,Integer typeId, Integer page,
			int pageSize,Date startTime,Date endTime) throws Exception {
		PageHelper.startPage(page, pageSize, true);

		CustomerRecordListDTO dto = new CustomerRecordListDTO();
		dto.setAccountId(accountId);
		dto.setCustomerId(customerId);
		dto.setTypeId(typeId);
		dto.setLoginId(UserState.getLoginId());
		dto.setDeptLeader(deptLeader==1);
		if(startTime!=null){
			dto.setStartTime(startTime);
		}
		if(endTime!=null){
			dto.setEndTime(endTime);
		}
		Page<CustomerRecordDTO> list = (Page<CustomerRecordDTO>) this.customerRecordDAO.findAllByCustomerId(dto);
		
		for(CustomerRecordDTO item:list)
		{
			item.setListCustomerRecordRevert(customerRecordRevertDAO.selectBy(item.getId()));
		}
		
		ServiceResult<Page<CustomerRecordDTO>> result = new ServiceResult<Page<CustomerRecordDTO>>(list);
		return result;
	}
	
	@Override
	public ServiceResult<Page<CustomerRecordDTO>> findAllByCustomerId(Integer deptLeader,Integer customerId, Integer accountId,Integer typeId, Integer page,
			int pageSize) throws Exception {
		PageHelper.startPage(page, pageSize, true);

		CustomerRecordListDTO dto = new CustomerRecordListDTO();
		dto.setAccountId(accountId);
		dto.setCustomerId(customerId);
		dto.setTypeId(typeId);
		dto.setLoginId(UserState.getLoginId());
		dto.setDeptLeader(deptLeader==1);
		Page<CustomerRecordDTO> list = (Page<CustomerRecordDTO>) this.customerRecordDAO.findAllByCustomerId(dto);
		
		for(CustomerRecordDTO item:list)
		{
			item.setListCustomerRecordRevert(customerRecordRevertDAO.selectBy(item.getId()));
		}
		
		ServiceResult<Page<CustomerRecordDTO>> result = new ServiceResult<Page<CustomerRecordDTO>>(list);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public LikeItReponseDTO like(Integer loginId, Integer recordId) {
		LikeItDTO dto = new LikeItDTO();
		LikeItReponseDTO result = new LikeItReponseDTO();
		dto.setLoginId(loginId);
		dto.setRecordId(recordId);

		Integer i = 0;
		try {

			boolean isLiked = this.customerRecordDAO.isLiked(dto) > 0;
			if (isLiked) {
				result.setLiked(isLiked);
				result.setSuccess(false);
				result.setMsg("已经赞过");
				logger.info("点赞=======================================" + result.toString());
				return result;
			} else {
				i = this.customerRecordDAO.insertRecordPraise(dto);
			}
			if (i > 0) {// 如果点赞成功，返回新的点赞数量以及是否已经赞过
				result.setSuccess(true);
				result.setMsg("点赞成功");
			} else {
				result.setSuccess(false);
				result.setMsg("点赞失败");
			}

			isLiked = this.customerRecordDAO.isLiked(dto) > 0;
			Integer likeQty = this.customerRecordDAO.getLikeQty(recordId);
			result.setLiked(isLiked);
			result.setLikeQty(likeQty);
			logger.info("点赞=======================================" + result.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public boolean insertRecord(CustomerRecord vo) throws Exception {
		Date date = new Date();
		Integer accountId = UserState.getLoginId();
		LastTimeDTO ltdto = new LastTimeDTO();
		ltdto.setAccountId(accountId);
		ltdto.setCustomerId(vo.getCustomerId());
		Date lastTime = this.customerRecordDAO.getLastTimeByAccountId(ltdto);
		if (lastTime == null) {
			lastTime = date;
		}
		vo.setLastTime(lastTime);
		vo.setAccountId(accountId);
		vo.setSource(1);// 来源：1 PC; 2 手机
		vo.setCreateTime(date);
		vo.setCommunicationTime(date);
		Integer i = this.customerRecordDAO.insertRecord(vo);
		return i > 0;
	}

	@Override
	public List<CustomerRecordDTO> findAllpublishers(Integer customerId,Integer state) throws Exception {
		// TODO Auto-generated method stub
		CustomerRecordListDTO dto=new CustomerRecordListDTO();
		dto.setCustomerId(customerId);
		dto.setState(state);
		return this.customerRecordDAO.findAllpublishers(dto);
	}
	
	@Override
	public CustomerRecordShareDTO getIsShare(CustomerRecordIsShare share) {
		return customerRecordDAO.getIsShare(share);
	}

	@Override
	public Page<CustomerRecordLogsDTO> findAllCustomerRecordLogsBy(CustomerRecordSearchForm dto) {
		PageHelper.startPage(dto.getPage(), dto.getPageSize(), true);
		Page<CustomerRecordLogsDTO> list = (Page<CustomerRecordLogsDTO>) this.customerRecordDAO.findAllCustomerRecordLogsBy(dto);
		return list;
	}

	@Override
	public ServiceResult<Page<CustomerRecordDTO>> findAllByCustomerIdGet(Integer deptLeader, Integer customerId,
			Integer accountId, Integer typeId, Integer page, int pageSize) throws Exception {
		PageHelper.startPage(page, pageSize, true);

		CustomerRecordListDTO dto = new CustomerRecordListDTO();
		dto.setAccountId(null);
		dto.setCustomerId(customerId);
		dto.setTypeId(typeId);
		dto.setLoginId(UserState.getLoginId());
		dto.setDeptLeader(deptLeader==1);
		Page<CustomerRecordDTO> list = (Page<CustomerRecordDTO>) this.customerRecordDAO.findAllByCustomerId(dto);
		
		for(CustomerRecordDTO item:list)
		{
			item.setListCustomerRecordRevert(customerRecordRevertDAO.selectBy(item.getId()));
		}
		
		ServiceResult<Page<CustomerRecordDTO>> result = new ServiceResult<Page<CustomerRecordDTO>>(list);
		return result;
	}
}
