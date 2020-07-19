package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.DictEasy;

public interface IDictDAO {

	public List<DictEasy> getDictEasy();
	/**
	 * 根据父级Id 获取所有子项集合
	 * @return
	 */
	public List<DictEasy> selectDictByPid(int parentId);
	
}
