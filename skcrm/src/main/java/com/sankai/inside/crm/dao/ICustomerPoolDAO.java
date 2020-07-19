package com.sankai.inside.crm.dao;

import java.util.List;
import java.util.Map;

import com.sankai.inside.crm.entity.CustomerNoTraceDto;

public interface ICustomerPoolDAO {
	/**
	 * 查看当天有没有执行跑批
	 * @return
	 * @throws Exception
	 */
	public Integer isRun() throws Exception;
	/**
	 * 增加跑批记录
	 * @return
	 * @throws Exception
	 */
	public Integer doCreateQuartzRecord() throws Exception;
	/**
	 * 统计多少天没有跟踪，超过配置的时间则进公海
	 *<!-- 初步沟通 30天  1  -->
	 *<!-- 见面拜访15天 2  -->
	 *<!-- 确定意向10天  3  -->
	 *<!-- 正式报价 7天  4  -->
	 *<!-- 商务洽谈7天  5  -->
	 * @param checkDate
	 * @param statusType
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getCostomerIdsUnfollowed(Map<String,Object>map) throws Exception;
	
	/**
	 * 超过180天且没有签约成功的客户列表
	 * @param status 状态集合
	 * @param day 天数
	 * @param NotAccounts 不受公海影响的用户 
	 * @return
	 */
	public List<Integer> getCostomerIdsUnSigned(String status,Integer day,String notAccounts);
	/**
	 * 修改sys_customer state 删除状态：-1 删除；0 正常 1公海，进公海
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer moveCustomerToPool(Integer id) throws Exception;
	/**
	 * 去除特定账号和跟踪记录超过X天的数据
	 * @param dto
	 * @return
	 */
	public List<CustomerNoTraceDto> selectCustomerNoTrace(CustomerNoTraceDto dto);
}
