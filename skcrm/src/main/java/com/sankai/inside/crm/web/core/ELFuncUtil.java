package com.sankai.inside.crm.web.core;

import com.sankai.inside.crm.core.utils.SpringBeanUtil;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.Dict;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.DepartmentService;
import com.sankai.inside.crm.service.ISysDictService;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class ELFuncUtil {
	
	
	static{
		accountService = SpringBeanUtil.getBean(AccountService.class);
		departmentService = SpringBeanUtil.getBean(DepartmentService.class);
		sysDictService = SpringBeanUtil.getBean(ISysDictService.class);
	}
	private static AccountService accountService;
	private static DepartmentService departmentService;
	private static ISysDictService sysDictService;

	public static String executeWay(String val){
			if (StringUtils.isEmpty(val)) {
			return "";
		}
		String names = "";
		val += ",";
		if(val.contains("1,")){
			names += "电话,";
		}
		if(val.contains("2,")){
			names += "微信,";
		}
		if(val.contains("3,")){
			names += "邮件,";
		}
		if(val.contains("4,")){
			names += "当面开会,";
		}
		if(val.contains("5,")){
			names += "视频会议,";
		}
		return names.substring(0, names.length() - 1);
	}
	public static String backWay(String val){
		if (StringUtils.isEmpty(val)) {
			return "";
		}
		String name = "";
		if(Objects.equals("1",val)){
			name = "提前一天";
		}else if(Objects.equals("2",val)){
			name = "具体时间";
		}else if(Objects.equals("3",val)){
			name = "具体节点";
		}else if(Objects.equals("4",val)){
			name = "不提醒";
		}else if(Objects.equals("5",val)){
			name = "搁置";
		}
		return name;
	}

	public static String sex(Integer val){
		switch (val) {
			case 1:
				return "男";
			case 0:
				return "女";
			default:
				return "未知";
		}
	}

    public static String getAccountName(Integer id){
    	if(id==null || id == 0) return "<lable style='color:red'>无</lable>";
    	Account result = accountService.getNameAndStateById(id);
    	if(result == null) return "<lable style='color:red'>无</lable>";
    	return result.getName() + (result.getState() == -1 ? "（<lable style='color:red'>已删除</lable>）":"");
    }
    
    public static String getDictName(Integer id){
    	if(id==null || id == 0) return "<lable style='color:red'>无</lable>";
    	Dict result = sysDictService.getDictById(id);
    	if(result == null) return "<lable style='color:red'>无</lable>";
    	return result.getName() + (result.getState() == -1 ? "（<lable style='color:red'>已删除</lable>）":"");
    }
    
    public static String getDeptName(Integer id){
    	if(id==null || id == 0) return "<lable style='color:red'>无</lable>";
    	Department result = departmentService.getDeptById(id);
    	if(result == null) return "<lable style='color:red'>无</lable>";
    	return result.getName() + (result.getState() == -1 ? "（<lable style='color:red'>已删除</lable>）":"");
    }
}
