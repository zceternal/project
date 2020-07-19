/*package com.sankai.inside.crm.web.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

*//**
 * #默认用户名 ACCOUNT_NAME=三开 #默认密码 SUPER_PWD=lenghonghao #默认ID default_id=0 #默认头像
 * @author Administrator
 * 
 *//*
public class SuperAccount {
	private static String account_id;
	
	private static String login_name;
	private static String login_pwd;
	private static String state;
	private static String sex;
	private static String name;
	private static String name_simple_py;
	private static String name_py;
	private static String is_dept_manager;

	
	static {
		//String path = getPath("src-resources", "defaultUser.properties");
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = SuperAccount.class.getResourceAsStream("/super_account.properties");
			properties.load(is);
			login_name = new String(properties.getProperty("login_name")
					.trim().getBytes("ISO-8859-1"),"UTF-8" );
			login_pwd = properties.getProperty("login_pwd").trim();
			account_id = properties.getProperty("account_id").trim();
			state = properties.getProperty("state").trim();
			sex = properties.getProperty("sex").trim();
			name = new String(properties.getProperty("name").trim().getBytes("ISO-8859-1"),"UTF-8");
			name_simple_py = properties.getProperty("name_simple_py").trim();
			name_py = properties.getProperty("name_py").trim();
			is_dept_manager = properties.getProperty("is_dept_manager").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public static void main(String[] args) {
		System.out.println(getPath("src-resources", "defaultUser.properties"));
		System.out.println(account_id);
		System.out.println(login_name);
		System.out.println(login_pwd);
		System.out.println(state);
		System.out.println(sex);
		
		
	}
	public static String getPath(String parentDir, String fileName) {
		String path = null;
		String userdir = System.getProperty("user.dir");
		String userdirName = new File(userdir).getName();
		if (userdirName.equalsIgnoreCase("lib")
		|| userdirName.equalsIgnoreCase("bin")) {
			File newf = new File(userdir);
			File newp = new File(newf.getParent());
			if (fileName.trim().equals("")) {
				path = newp.getPath() + File.separator + parentDir;
			} else {
				path = newp.getPath() + File.separator + parentDir
				+ File.separator + fileName;
			}
		} else {
			if (fileName.trim().equals("")) {
				path = userdir + File.separator + parentDir;
			} else {
				path = userdir + File.separator + parentDir + File.separator
						+ fileName;
			}
		}
		return path;
	}

	public static String getAccount_id() {
		return account_id;
	}
	public static void setAccount_id(String account_id) {
		SuperAccount.account_id = account_id;
	}
	public static String getLogin_name() {
		return login_name;
	}
	public static void setLogin_name(String login_name) {
		SuperAccount.login_name = login_name;
	}
	public static String getLogin_pwd() {
		return login_pwd;
	}
	public static void setLogin_pwd(String login_pwd) {
		SuperAccount.login_pwd = login_pwd;
	}
	public static String getState() {
		return state;
	}
	public static void setState(String state) {
		SuperAccount.state = state;
	}
	public static String getSex() {
		return sex;
	}
	public static void setSex(String sex) {
		SuperAccount.sex = sex;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		SuperAccount.name = name;
	}
	public static String getName_simple_py() {
		return name_simple_py;
	}
	public static void setName_simple_py(String name_simple_py) {
		SuperAccount.name_simple_py = name_simple_py;
	}
	public static String getName_py() {
		return name_py;
	}
	public static void setName_py(String name_py) {
		SuperAccount.name_py = name_py;
	}
	
	public static String getIs_dept_manager() {
		return is_dept_manager;
	}
	public static void setIs_dept_manager(String is_dept_manager) {
		SuperAccount.is_dept_manager = is_dept_manager;
	}
}
*/