package com.sankai.inside.crm.entity;

public class AddressSearchDTO {
	private boolean isSuccess;
	
	private String pCode;
	private String pName;//省
	
	private String cCode;
	private String cName;//市
	
	private String aCode;
	private String aName;//县
	
	private String fullName;

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getaCode() {
		return aCode;
	}

	public void setaCode(String aCode) {
		this.aCode = aCode;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "AddressSearchDTO [isSuccess=" + isSuccess + ", pCode=" + pCode + ", pName=" + pName + ", cCode=" + cCode
				+ ", cName=" + cName + ", aCode=" + aCode + ", aName=" + aName + ", fullName=" + fullName + "]";
	}

	
	
}
