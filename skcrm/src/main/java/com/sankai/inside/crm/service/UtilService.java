package com.sankai.inside.crm.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sankai.inside.crm.core.utils.SpringBeanUtil;
import com.sankai.inside.crm.dao.IAccountDAO;
import com.sankai.inside.crm.dao.IDepartmentDAO;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.web.core.UserState;

public class UtilService {

	private static IAccountDAO accountDAO = SpringBeanUtil.getBean(IAccountDAO.class);

	private static IDepartmentDAO deptDAO = SpringBeanUtil.getBean(IDepartmentDAO.class);

	/**
	 * 根据当前登录id 获取当前用户所在部门和子部门的所有id； 如果是管理员 则返回 -1
	 * 
	 * @return
	 */
	public static String getDeptChilds() {

		Integer loginId = UserState.getLoginId();
		if (loginId <= 0)
			return "-1";

		List<Department> source = deptDAO.selectAll();// 所有部门

		int deptId = accountDAO.getDeptIdById(loginId);// 根据当前登录Id获取部门Id
		String result = "";
		for (Department item : source) {
			if (item.getPid() == deptId) {
				int id = item.getId();
				result += id + ",";
				result += getDeptChilds(source, id);
			}
		}
		return result + deptId;
	}

	/**
	 * 根据当前登录id 获取当前用户所在部门的所有子部门的id【注：不包含当前部门】
	 * 
	 * @param source
	 *            部门集合
	 * @param deptId
	 *            当前用户所在部门id
	 * @return
	 */
	public static String getDeptChilds(List<Department> source, int deptId) {
		String result = "";
		for (Department item : source) {
			if (item.getPid() == deptId) {
				int id = item.getId();
				result += id + ",";
				result += getDeptChilds(source, id);
			}
		}
		return result;
	}

	/**
	 * 数字转字符串
	 * 
	 * @param array
	 *            数组
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static String ArrayTranString(Object[] array, char separator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(((String) array[i]).trim() + separator);
		}
		String newStr = sb.toString();
		return newStr.substring(0, newStr.length() - 1);
	}

	/**
	 * 根据日期计算年龄
	 * 
	 * @param dateOfBirth 生日
	 * @return
	 */
	public static int getAge(Date dateOfBirth) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (dateOfBirth != null) {
			now.setTime(new Date());
			born.setTime(dateOfBirth);
			if (born.after(now)) {
				throw new IllegalArgumentException("参数日期为未来日期！");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return age;
	}
	/**
	 * 根据日期计算年龄
	 * 
	 * @param dateOfBirth 生日
	 * @return
	 */
	public static int getAge(String dateOfBirth) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (!StringUtils.isEmpty(dateOfBirth)) {
			now.setTime(new Date());
			born.setTime(new Date(dateOfBirth));
			if (born.after(now)) {
				throw new IllegalArgumentException("参数日期为未来日期！");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return age;
	}

}
