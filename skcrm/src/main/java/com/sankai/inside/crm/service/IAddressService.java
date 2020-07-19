package com.sankai.inside.crm.service;

import java.util.List;

import com.sankai.inside.crm.entity.Address;



public interface IAddressService {
	/**
	 * 列出所有的省份
	 * @return
	 * @throws Exception
	 */
	public List<Address> listAllProvs() throws Exception;
	
	/**
	 * 根据省份列出城市、地区
	 * @return
	 * @throws Exception
	 */
	public List<Address> listCityDicByProv(int parentId) throws Exception;
	
	/**
	 * 根据code查出对应的省市县 用于自定义标签  在contact_show.jsp使用
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getNameByCode(String code) throws Exception;
}
