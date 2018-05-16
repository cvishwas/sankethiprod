package com.support.common.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class FilePollerDetail implements Serializable, RowMapper<FilePollerDetail>{
	
	private long Id;
	private String documentBarcode="";
	private String shipmentNumber="";
	private String guid="";
	private String pdfFileName="";
	private String pdfFilePath="";
	private String xmlFileName="";
	private Date createdDate;
	private Date updatedDate;
	private Date batchDate;
	private String status="";
	private boolean reProcess;
	private boolean del_indctr;
	private String sourceSystem="";
	private int priority_cd;
	private String error="";
	private Date reProcessDate;
	private String error_cat="";
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getDocumentBarcode() {
		return documentBarcode;
	}
	public void setDocumentBarcode(String documentBarcode) {
		this.documentBarcode = documentBarcode;
	}
	public String getShipmentNumber() {
		return shipmentNumber;
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public String getPdfFilePath() {
		return pdfFilePath;
	}
	public void setPdfFilePath(String pdfFilePath) {
		this.pdfFilePath = pdfFilePath;
	}
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isReProcess() {
		return reProcess;
	}
	public void setReProcess(boolean reProcess) {
		this.reProcess = reProcess;
	}
	public boolean isDel_indctr() {
		return del_indctr;
	}
	public void setDel_indctr(boolean del_indctr) {
		this.del_indctr = del_indctr;
	}
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	
	
	public Date getBatchDate() {
		return batchDate;
	}
	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}
	public int getPriority_cd() {
		return priority_cd;
	}
	public void setPriority_cd(int priority_cd) {
		this.priority_cd = priority_cd;
	}
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	public String getError_cat() {
		return error_cat;
	}
	public void setError_cat(String error_cat) {
		this.error_cat = error_cat;
	}
	public Date getReProcessDate() {
		return reProcessDate;
	}
	public void setReProcessDate(Date reProcessDate) {
		this.reProcessDate = reProcessDate;
	}
	@Override
	public String toString() {
		return "FilePollerDetail [Id=" + Id + ", documentBarcode=" + documentBarcode + ", shipmentNumber="
				+ shipmentNumber + ", guid=" + guid + ", pdfFileName=" + pdfFileName + ", pdfFilePath=" + pdfFilePath
				+ ", xmlFileName=" + xmlFileName + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", batchDate=" + batchDate + ", status=" + status + ", reProcess=" + reProcess + ", del_indctr="
				+ del_indctr + ", sourceSystem=" + sourceSystem + ", priority_cd=" + priority_cd + ", error=" + error
				+ ", reProcessDate=" + reProcessDate + ", error_cat=" + error_cat + "]";
	}
	@Override
	public FilePollerDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		FilePollerDetail fpd = new FilePollerDetail();
		fpd.setId(rs.getLong("FILE_POLLER_ID"));
		fpd.setDocumentBarcode(rs.getString("DCMNT_BAR_CD"));
		fpd.setShipmentNumber(rs.getString("SHIPMENT_NUM"));
		fpd.setPriority_cd(rs.getInt("PRIORITY_CD"));
		fpd.setBatchDate(rs.getDate("BTCH_DT"));
		fpd.setGuid(rs.getString("BTCH_GUID"));
		fpd.setPdfFileName(rs.getString("FILE_NM"));
		fpd.setPdfFilePath(rs.getString("FILE_PATH"));
		fpd.setXmlFileName(rs.getString("XML_FILE_NM"));
		fpd.setCreatedDate(rs.getDate("CR_DT"));
		fpd.setUpdatedDate(rs.getDate("UPD_DT"));
		fpd.setStatus(rs.getString("PRCSD_STTS"));
		fpd.setError(rs.getString("ERROR"));
		fpd.setReProcess(rs.getBoolean("RE_PRCS"));
		fpd.setReProcessDate(rs.getDate("RE_PRCS_DT"));
		fpd.setDel_indctr(rs.getBoolean("DEL_INDCTR"));
		fpd.setSourceSystem(rs.getString("SRC_SYSTM"));
		fpd.setError_cat(rs.getString("ERROR_CAT"));
		return fpd;
	}
	
	

}
