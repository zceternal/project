package com.sankai.inside.crm.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sankai.inside.crm.core.utils.Pinyin4jUtil;
import com.sankai.inside.crm.entity.Customer;
import com.sankai.inside.crm.entity.ImportExcelEntity;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.ContactForm;

import ch.qos.logback.core.pattern.Converter;


@Service
public class CustomerImportService {

	@Resource
	private ICustomerService CustomerService;//客户
	
	@Resource
	private ContactService ContactService;//联系人
	
	public ServiceResultBool CusImport(List<ImportExcelEntity> list) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Customer customer=new Customer();//实体化要新增的客户类
		ServiceResult result=null;
		
		for (ImportExcelEntity importExcelEntity : list) {//循环Excel中的list集合
		if (importExcelEntity.getCustomerAbbreviation()==""||importExcelEntity.getCustomerAbbreviation()==null) {
			importExcelEntity.setCustomerAbbreviation(importExcelEntity.getCustomerName());//如果简写为空  则用名字代替
		}	
		
		if (importExcelEntity.getCustomerName()==""&&importExcelEntity.getContactNmae()==""&&importExcelEntity.getPhone()=="") {//判断客户名字是否为空
			return new ServiceResultBool("客户姓名和联系人名字和联系人电话为空的条目以上数据导入完成");
		}
		/*
		 * 客户集合赋值
		 * */	
		customer.setName(importExcelEntity.getCustomerName());

		customer.setCreateId(UserState.getLoginId());
		customer.setShortName(importExcelEntity.getCustomerAbbreviation());
		customer.setAddress(importExcelEntity.getAddress());
		
		if (importExcelEntity.getCreateTime()==""||importExcelEntity.getCreateTime()==null) {
			customer.setCreateTime(new Date());
		}else{
			Date date=new Date(importExcelEntity.getCreateTime());
			customer.setCreateTime(date);
		}
		
		customer.setState(0);
		customer.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(importExcelEntity.getCustomerName()));
		customer.setNamePy(Pinyin4jUtil.getPinYin(importExcelEntity.getCustomerName()));
		
		if (importExcelEntity.getCustomerName()!=""&&importExcelEntity.getCustomerName()!=null) {
			result= CustomerService.addCustomerImport(customer);
		}
		
//		if (!result.isSuccess()) {//判断是否导入成功   如果没有   则返回错误提示
//			return new ServiceResultBool(false,result.getMsg());
//		}
		
		if ((importExcelEntity.getContactNmae()==""||importExcelEntity.getContactNmae()==null)||(importExcelEntity.getPhone()==""||importExcelEntity.getPhone()==null)) {
			
		}else{
				ContactForm model=new ContactForm();

				if (importExcelEntity.getCreateTime()==""||importExcelEntity.getCreateTime()==null) {
					model.setCreateTime(new Date());
				}else{
					Date date=new Date(importExcelEntity.getCreateTime());
					model.setCreateTime(date);
				}
				
				model.setCreateId(UserState.getLoginId());// 登录人id
				model.setBirthday(new Date());
				model.setSort(1); // 设置置顶最大值
				model.setName(importExcelEntity.getContactNmae());
				if (!result.isSuccess()) {
					Integer cid = CustomerService.getCustomerId(importExcelEntity.getCustomerName());
					model.setCustomerId(cid==null?0:cid);
				}else{
					model.setCustomerId((Integer)result.getData());
				}
				if(model.getCustomerId()==null)
				model.setCustomerId(0);
				if (importExcelEntity.getSex()=="男") {
					model.setSex(1);
				}else if(importExcelEntity.getSex()=="女"){
					model.setSex(0);
				}else if(importExcelEntity.getSex()==""||importExcelEntity.getSex()==null){
					model.setSex(1);
				}else {
					model.setSex(2);
				}
				model.setPhone(importExcelEntity.getPhone());
				
				ServiceResultBool res= ContactService.addImport(model);
				
//				if (!res.isSuccess()) {//判断联系人是否导入成功   如果没有   则返回错误提示
//					return new ServiceResultBool(false,res.getMsg());
//				}
			}
			
		}
		return new ServiceResultBool("导入成功");
	}
	
	
}
