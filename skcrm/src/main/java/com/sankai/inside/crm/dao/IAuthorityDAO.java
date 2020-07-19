package com.sankai.inside.crm.dao;

import java.util.List;
import java.util.Map;

import com.sankai.inside.crm.entity.Authority;

public interface IAuthorityDAO {
	
    public List<Authority> selectByDept(int deptId);
    
    public List<Authority> selectByAccount(int accountId);
    
    public void insertBatch(List<Authority> list);
    
    public void deleteBatch(Map map);
	
}
