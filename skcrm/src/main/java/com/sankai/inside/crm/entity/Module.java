package com.sankai.inside.crm.entity;

import java.util.List;

public class Module {

	private String groupName;
	
	private boolean checked;

	private List<ModuleItem> items;
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	

	public List<ModuleItem> getItems() {
		return items;
	}

	public void setItems(List<ModuleItem> items) {
		this.items = items;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
	
}

