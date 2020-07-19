package com.sankai.inside.crm.web.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*import org.aspectj.weaver.ast.Var;*/

/**
 * 扩展类
 * 
 * @author Zzc
 *
 */
public class ExtendFunc {

	/**
	 * 缩略字符串长度
	 * 
	 * @param val
	 * @param length
	 * @return
	 */
	public static String subStr(String val, Integer length) {
		if (length == null || length == 0 || val.length() <= (length))
			return val;
		return val.substring(0, length - 1) + "...";
	}

	/**
	 * 根据字符长度添加<br>标签
	 * @param val
	 * @param length
	 * @return
	 */
	public static String subStrByBr(String val, Integer length) {
		if (length == null || length <= 0 || val.length() <= (length))
			return val;
		Integer brs = val.length()/length;
		String result = "";
		if(brs>=1 && val.length()>length)//循环添加br
		{
			for(int i=0;i<brs;i++)
			{
				result+=val.substring(i*length, (i+1)*length)+"</br>";
			}
			result += val.substring(brs*length);
		}
		
		
		return result;
	}
	
	
	/**
     * 通过递归删除html标签
     * @param content - 包含HTML标签的内容 
     * @return 不带HTML标签的文本内容
     */
	public static String removeTag(String content) {
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>");
        Matcher m = p.matcher(content);
        if (m.find()) {
            content = content.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
            content = removeTag(content);
        }
        return content;
    }
}
