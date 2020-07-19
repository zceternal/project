package com.sankai.inside.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sankai.inside.crm.dao.IAddressDAO;
import com.sankai.inside.crm.entity.Address;
import com.sankai.inside.crm.service.IAddressService;

@Service("addressServiceImpl")
public class AddressServiceImpl implements IAddressService {
	@Resource
	private IAddressDAO addressDAO;

	@Override
	public List<Address> listAllProvs() throws Exception {
		return this.addressDAO.listAllProv();
	}

	@Override
	public List<Address> listCityDicByProv(int parentId) throws Exception {
		return this.addressDAO.listCityDicByProv(parentId);
	}

	@Override
	public String getNameByCode(String code) throws Exception {
		
		return this.addressDAO.findNameByCode(code);
	}

}
