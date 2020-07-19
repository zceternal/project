package com.sankai.inside.crm.core.utils;

import java.util.List;
import java.util.stream.Collectors;

public class MathUtil {

	public static TransformResult Transform(List<String> source, List<String> newList) {

		TransformResult result = new TransformResult();

		// 如果原有项为空，则进行新增操作
		if (source == null) {
			result.setNewList(newList);
			return result;
		}
		// 如果新建项为空，则进行删除操作
		if (newList == null) {
			result.setDeleList(source);
			return result;
		}

		List<String> list = newList.stream().filter(x -> !source.contains(x)).collect(Collectors.toList());

		result.setNewList(list);

		List<String> deleList = source.stream().filter(x -> !newList.contains(x)).collect(Collectors.toList());
		
		result.setDeleList(deleList);

		/*
		 * var list = newList.Where(item => !source.Contains(item)).ToList();
		 * 
		 * deleList = source.Where(item => !newList.Contains(item)).ToList();
		 */

		return result;
	}
}
