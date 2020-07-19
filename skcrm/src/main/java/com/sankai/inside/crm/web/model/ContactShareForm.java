package com.sankai.inside.crm.web.model;

import java.util.List;

public class ContactShareForm {
	private List<String> contactId;
	private List<String> allowAccountId;
	
	public List<String> getContactId() {
		return contactId;
	}
	public void setContactId(List<String> contactId) {
		this.contactId = contactId;
	}
	public List<String> getAllowAccountId() {
		return allowAccountId;
	}
	public void setAllowAccountId(List<String> allowAccountId) {
		this.allowAccountId = allowAccountId;
	}
}
