package com.sankai.inside.crm.web.controllers;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sankai.inside.crm.entity.Address;
import com.sankai.inside.crm.entity.AddressSearchDTO;
import com.sankai.inside.crm.service.IAddressService;

@Controller
public class AddressController {
	@Resource
	private IAddressService addressService;
	
	//根据省份列出城市、县区
	@ResponseBody
	@RequestMapping(value="address/listCitiesDics",method=RequestMethod.POST)
	public Object listAllProvs(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj=new JSONObject();
		JSONArray arr=new JSONArray();
		int parentId=Integer.parseInt(request.getParameter("parentId"));
		try {
			List<Address> list= this.addressService.listCityDicByProv(parentId);
			Iterator<Address> iter=list.iterator();
			while(iter.hasNext()){
				Address address=iter.next();
				JSONObject temp=new JSONObject();
				temp.put("id", address.getId());
				temp.put("code", address.getCode());
				temp.put("parentId", address.getParentId());
				temp.put("name", address.getName());
				temp.put("level", address.getLevel());
				arr.add(temp);
			}
			obj.put("allData", arr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	//根据省份列出城市、县区
	@ResponseBody
	@RequestMapping(value="address/get_all_province",method=RequestMethod.POST)
	public Object getAllProvinces(HttpServletRequest request){
		JSONObject obj=new JSONObject();
		JSONArray arr=new JSONArray();
		try {
			List<Address> list= this.addressService.listAllProvs();
			Iterator<Address> iter=list.iterator();
			while(iter.hasNext()){
				Address address=iter.next();
				JSONObject temp=new JSONObject();
				temp.put("id", address.getId());
				temp.put("code", address.getCode());
				temp.put("parentId", address.getParentId());
				temp.put("name", address.getName());
				temp.put("level", address.getLevel());
				arr.add(temp);
			}
			obj.put("allData", arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	@ResponseBody
	@RequestMapping(value="address/fullName",method=RequestMethod.POST)
	public AddressSearchDTO getFullName(AddressSearchDTO dto){
		AddressSearchDTO result=new AddressSearchDTO();
		String pCode=dto.getpCode();
		String cCode=dto.getcCode();
		String aCode=dto.getaCode();
		
		try {
			String pName=this.addressService.getNameByCode(pCode);
			String cName=this.addressService.getNameByCode(cCode);
			String aName=this.addressService.getNameByCode(aCode);
			
			result.setpName(pName);
			result.setcName(cName);
			result.setaName(aName);
			result.setSuccess(true);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
		
	}
}
