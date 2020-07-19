package com.sankai.inside.crm.core.utils;

import java.util.ArrayList;
import java.util.List;

public class TransformResult {
	
	private List<String> newList;
	
	private List<String> deleList;
	
	

	public TransformResult() {
		newList = new ArrayList<String>();
		deleList= new ArrayList<String>();
	}

	public List<String> getNewList() {
		return newList;
	}

	public void setNewList(List<String> newList) {
		this.newList = newList;
	}

	public List<String> getDeleList() {
		return deleList;
	}

	public void setDeleList(List<String> deleList) {
		this.deleList = deleList;
	}
	
	
}
