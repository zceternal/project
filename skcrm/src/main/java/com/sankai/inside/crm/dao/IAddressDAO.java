package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.Address;

public interface IAddressDAO {
	
	/**
	 * 列出所有的省份
	 * @return
	 * @throws Exception
	 */
	public List<Address> listAllProv() throws Exception;
	
	
	/**
	 * 根据省份/城市 列出城市、地区
	 */
	public List<Address> listCityDicByProv(Integer parentId) throws Exception;
	/**
	 * 根据code查出对应的省市县
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String findNameByCode(String code) throws Exception;
	
}
