package com.support.common.beans;

public class DateSearch {
	public String fromDate;
	public String toDate;
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		return "DateSearch [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
	
	

}
