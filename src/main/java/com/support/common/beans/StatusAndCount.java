package com.support.common.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class StatusAndCount implements Serializable, RowMapper<StatusAndCount>{
	private String shipmentNum;
	private String Status;
	private int count;
	
	
	
	public String getShipmentNum() {
		return shipmentNum;
	}



	public void setShipmentNum(String shipmentNum) {
		this.shipmentNum = shipmentNum;
	}



	public String getStatus() {
		return Status;
	}



	public void setStatus(String status) {
		Status = status;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	@Override
	public StatusAndCount mapRow(ResultSet rs, int rowNum) throws SQLException {

		StatusAndCount statusAndCount = new StatusAndCount();
		statusAndCount.setStatus(rs.getString("STATUS"));
		statusAndCount.setCount(rs.getInt("COUNT"));
		statusAndCount.setShipmentNum(rs.getString("shipmentNum"));
		return statusAndCount;
	}

}
