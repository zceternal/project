package com.sankai.inside.crm.web.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 跟踪客户状态
 * @author Zzc
 *
 */

public enum TraceType {
	
	TODAY_TRACE {
		public String getName() {
			return "当天跟踪过";
		}
	},
	ONEDAY_TRACE {
		public String getName() {
			return "1天前跟踪过";
		}
	},
	TWODAY_TRACE {
		public String getName() {
			return "2天前跟踪过";
		}
	},
	THREEDAY_TRACE {
		public String getName() {
			return "3天前跟踪过";
		}
	},
	FOURDAY_TRACE {
		public String getName() {
			return "4天前跟踪过";
		}
	},
		FIVEDAY_TRACE {
		public String getName() {
			return "5天前跟踪过";
		}
	},
	SIXDAY_TRACE {
		public String getName() {
			return "6天前跟踪过";
		}
	},
	SEVEN_NOTRACE {
		public String getName() {
			return "7天未跟踪";
		}
	},
	TEN_NOTRACE {
		public String getName() {
			return "10天未跟踪";
		}
	},
	FIFTEEN_NOTRACE {
		public String getName() {
			return "15天未跟踪";
		}
	},
	THIRTY_NOTRACE {
		public String getName() {
			return "30天未跟踪";
		}
	},
	SIXTY_NOTRACE {
		public String getName() {
			return "60天未跟踪";
		}
	},
	NOBODY_TRACE {
		public String getName() {
			return "无联系人";
		}
	},
	UNDEFINED {
		public String getName() {
			return "未知";
		}
	};

	public abstract String getName();

	/**
	 * 根据当期跟踪日期判断跟踪状态
	 * @param date 跟踪日期
	 * @return
	 */
	public static TraceType CalcValue(String date) {
		Date currDate = new Date();
		Date myDate = new Date();

		DateFormat df = DateFormat.getDateInstance();
		try {
			myDate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int days = daysBetween(myDate, currDate);
		if (days == 0)
			return TraceType.TODAY_TRACE;
		else if (days < 0)
			return TraceType.UNDEFINED;
		else if (days == 1)
			return TraceType.ONEDAY_TRACE;
		else if (days == 2)
			return TraceType.TWODAY_TRACE;
		else if (days == 3)
			return TraceType.THREEDAY_TRACE;
		else if (days == 4)
			return TraceType.FOURDAY_TRACE;
		else if (days == 5)
			return TraceType.FIVEDAY_TRACE;
		else if (days == 6)
			return TraceType.SIXDAY_TRACE;
				
		else if (days >= 7 && days<10)
			return TraceType.SEVEN_NOTRACE;
		else if (days >= 10 && days<15)
			return TraceType.TEN_NOTRACE;
		else if (days >= 15&& days<30)
			return TraceType.FIFTEEN_NOTRACE;
		else if (days >= 30&& days<60)
			return TraceType.THIRTY_NOTRACE;
		else if (days >= 60 && days<365)
			return TraceType.SIXTY_NOTRACE;
		else
			return TraceType.NOBODY_TRACE;

	}

	/**
	 * 判断两个日期的相差天数
	 * @param early
	 * @param late
	 * @return
	 */
	public static final int daysBetween(Date early, Date late) {

		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		// 设置时间为0时
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}

}
