package com.sankai.inside.crm.dao;

import java.util.List;

import com.sankai.inside.crm.entity.Analysis;
import com.sankai.inside.crm.entity.AnalysisSearch;

public interface IAnalysis {
	
	public Integer list(AnalysisSearch entity);//酷虎数量
	
	public List<Analysis> count(AnalysisSearch entity);
	

}
