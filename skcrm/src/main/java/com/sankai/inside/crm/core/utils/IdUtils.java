package com.sankai.inside.crm.core.utils;

import java.math.BigInteger;
import java.util.Date;

public class IdUtils {
	/**
	 * 生成18位的唯一主键（15位时间戳+3位随机数）
	 * @return
	 */
	public static BigInteger uniqueId(){
		StringBuffer id = new StringBuffer();
		id.append(JodaUtils.dateToString(new Date(), JodaUtils.DATE_TIME_FORMAT4));
		id.append(String.valueOf(IdUtils.randomNum(3)));
		return new BigInteger((id.toString()));
	}
	
    /**
     * 生成N位随机数
     *
     * @return
     */
    public static int randomNum(int n) {
        return (int) (Math.random() * (0.9 * (int) Math.pow(10, n))) + (int) Math.pow(10, n - 1);
    }
	

}
