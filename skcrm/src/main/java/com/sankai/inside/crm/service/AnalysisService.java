package com.sankai.inside.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankai.inside.crm.dao.IAnalysis;
import com.sankai.inside.crm.dao.IDictDAO;
import com.sankai.inside.crm.entity.Analysis;
import com.sankai.inside.crm.entity.AnalysisSearch;
import com.sankai.inside.crm.entity.DictEasy;

@Service
public class AnalysisService {


	@Autowired
	private IAnalysis analysisDAO;
	@Autowired
	private IDictDAO dictDAO;



	public List<Analysis> list(AnalysisSearch entity){
		List<DictEasy> listDict=dictDAO.getDictEasy();
		List<Analysis> list=new ArrayList();
		for(DictEasy model:listDict)    {
			entity.setStatus(model.getId());
			Analysis anal=new Analysis();
			anal.setCount(analysisDAO.list(entity));
			anal.setStatuName(model.getName());
			 list.add(anal);
		}
		
		return list;
		}
	
	public List<Analysis> count(AnalysisSearch entity){
		List<Analysis> list=analysisDAO.count(entity);
		return list;
		}
}
