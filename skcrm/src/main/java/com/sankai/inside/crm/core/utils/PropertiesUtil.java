package com.sankai.inside.crm.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesUtil {
	private static Properties properties= new Properties();
	 /**属性文件名*/
    private static final String PROPERTIES_FILE_PATH="/customer_pool.properties";
    /**操作的键名*/
    public static String KEY_NAME="";
    
    
    /**
     * 初始化properties
     * @param path 属性文件路径,如:/config.properties
     * @throws Exception 
     */
    private static void initProperties() throws Exception {
        
        try {
        	if(!checkParams().equals("SUCC")) return;
            InputStream ipsm = PropertiesUtil.class.getResourceAsStream(PROPERTIES_FILE_PATH);
            properties.load(ipsm);
            ipsm.close();
        } catch (Exception e) {
        	 throw new Exception(e);
        }
    }

    /**
     * 根据键名key获取属性文件中对应的键值
     * @param path 属性文件路径,如:/config.properties
     * @param key 属性文件 键名
     * @return
     * @throws Exception 
     */
    public static String getValueByKey() throws Exception{
        //如果properties为空，则初始化
        if(properties.isEmpty())
            initProperties();
        return properties.getProperty(KEY_NAME);
    }
    
    /**
     * 通过键名key修改其对应的值为value
     * @param path 属性文件路径,如:/config.properties
     * @param key  属性文件 键名
     * @param value 属性文件 键值
     * @throws Exception 
     */
    public static void modifyValueByKey(String value) throws Exception {
        //如果properties为空，则初始化
        if(properties.isEmpty())
            initProperties();
        //修改值
        properties.setProperty(KEY_NAME, value);
       
        try {
            //获取文件路径
            URL url = PropertiesUtil.class.getResource(PROPERTIES_FILE_PATH);
            FileOutputStream fos = new FileOutputStream(new File(url.toURI()));
            //保存到文件
            properties.store(fos, "根据Key修改value");
            fos.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * 参数验证
     * @return
     */
    private static String checkParams()
    {
    	if(StringUtils.isBlank(PROPERTIES_FILE_PATH))
    	{
    		return "properties属性文件路径不能为空！";
    	}else if(StringUtils.isBlank(KEY_NAME))
    	{
    		return "标示key不能为空！";
    	}
    	return "SUCC";
    }
  
}
