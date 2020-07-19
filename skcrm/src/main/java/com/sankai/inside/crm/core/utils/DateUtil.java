package com.sankai.inside.crm.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
	/**
	 * 计算两个时间差值的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long compareDate4Day(Date startDate, Date endDate) {
		long l = endDate.getTime() - startDate.getTime();
		long d = l / (24 * 60 * 60 * 1000);
		return d;
	}
	/**
	 * 获取日期(yyyy-MM-dd)
	 * @param dateStr
	 * @return
	 */
	public static Date getDate(String dateStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
	}
	/*public static void main(String[] args) {
		long d = DateUtil.compareDate4Day(getDate("2017-03-06"), new Date());
		System.out.println(d);
	}*/
}
