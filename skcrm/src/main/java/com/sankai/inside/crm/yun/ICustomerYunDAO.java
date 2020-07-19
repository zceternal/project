package com.sankai.inside.crm.yun;

import java.util.List;

import com.sankai.inside.crm.entity.yun.CustomerYun;

public interface ICustomerYunDAO {
	
	public List<CustomerYun> getYunCustomerList(String startTime,String endTime) ;

}
