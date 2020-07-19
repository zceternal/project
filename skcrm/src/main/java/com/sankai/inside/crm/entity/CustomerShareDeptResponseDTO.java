package com.sankai.inside.crm.entity;

import java.util.List;

public class CustomerShareDeptResponseDTO {
	private boolean isSuccess;
	private List<CustomerShareDeptDTO> depts;

	public List<CustomerShareDeptDTO> getDepts() {
		return depts;
	}

	public void setDepts(List<CustomerShareDeptDTO> depts) {
		this.depts = depts;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "CustomerShareDeptResponseDTO [isSuccess=" + isSuccess + ", depts=" + depts + "]";
	}

	
	
}
