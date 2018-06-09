package com.sunjoy.trm.master.dao.enums;

public enum StudentStatus {
	VALID("VALID", "有效"), DELETED("DELETED", "删除");

	private String code;
	private String label;

	private StudentStatus(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return this.code;
	}

	public String getLabel() {
		return this.label;
	}

	@Override
	public String toString() {
		return this.name() + ":{code:" + this.getCode() + ",name:" + this.getLabel() + "}";
	}
}
