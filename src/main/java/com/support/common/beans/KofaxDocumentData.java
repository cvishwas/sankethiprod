package com.support.common.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;



public class KofaxDocumentData implements Serializable, RowMapper<KofaxDocumentData>{

	public static final String DOCUMENTS_XPATH = "/XMLRELEASE/KOFAXXML/BATCHCLASS/DOCUMENTS";

	public String getProcessedHostName() {
		return processedHostName;
	}

	public void setProcessedHostName(String processedHostName) {
		this.processedHostName = processedHostName;
	}

	public static final String KOFAX_DOCUMENT_LIST_TAG = "kfx:DOCUMENTS";
	public static final String KOFAX_DOCUMENT_TAG = "kfx:DOCUMENT";

	public static final String DOCUMENT_XPATH = "/XMLRELEASE/KOFAXXML/BATCHCLASS/DOCUMENTS/DOCUMENT";
	
	
	public static final String DOCUMENTFIELD_TAG = "kfx:DOCUMENTFIELD";
	public static final String PRIMARY_FILENAME_TAG = "kfx:PRIMARYFILE";
	
	public static final String BATCH_STATUS_TAG= "kfx:BATCHSTATUS";
	public static final String BATCH_CLASS_TAG= "kfx:BATCHCLASS";
	
	public static final String BATCH_CLASS_ID= "ID";
	
	public static final String DOCUMENT_ID_TAG = "DOCID";
	public static final String BARCODE_TAG = "Barcode";
	public static final String PRIORITY = "Priority";
	public static final String SHIPMENT_TRACKING_NUMBER = "ShipmentTrackingNo";
	public static final String BATCH_DATE = "BatchDate";
	public static final String BATCH_TIME = "BatchTime";
	public static final String BATCH_GUID = "BatchGuid";
	public static final String FILE_NAME = "FILENAME";

	public static final String POLLER_ERROR = "POLLER_ERROR";
	public static final String PROCESS_STATUS = "PROCESS_STATUS";

	private String id;
	private String batchClassId;
	private String docId;
	private String barcode;
	private String priority = "-1";
	private String shipmentTrackingNo;
	private String batchDate;
	private String batchTime;
	private String batchGuid;
	private String[] fileNames;
	private String[] filePaths;
	private String errorCategory = "";
	private String errorDescription = "";
	private String status = "success";
	private String processedHostName;
	private boolean hasError = false;
	private String fileMovedToFolderPostPrcssng = "";
	private String sourceSystem;
	private long filePollerId;
	private String reProcess;
	private String processOrReprocess = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getShipmentTrackingNo() {
		return shipmentTrackingNo;
	}

	public void setShipmentTrackingNo(String shipmentTrackingNo) {
		this.shipmentTrackingNo = shipmentTrackingNo;
	}

	public String getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(String batchDate) {
		this.batchDate = batchDate;
	}

	public String getBatchTime() {
		return batchTime;
	}

	public void setBatchTime(String batchTime) {
		this.batchTime = batchTime;
	}

	public String getBatchGuid() {
		return batchGuid;
	}

	public void setBatchGuid(String batchGuid) {
		this.batchGuid = batchGuid;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public static String getDocumentIdTag() {
		return DOCUMENT_ID_TAG;
	}

	public static String getDocumentsXpath() {
		return DOCUMENT_XPATH;
	}

	public String getBatchClassId() {
		return batchClassId;
	}

	public void setBatchClassId(String batchClassId) {
		this.batchClassId = batchClassId;
	}
	
	

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	} 
	
	 

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
	

	public String[] getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(String[] filePaths) {
		this.filePaths = filePaths;
	}

	public String getFileMovedToFolderPostPrcssng() {
		return fileMovedToFolderPostPrcssng;
	}

	public void setFileMovedToFolderPostPrcssng(String fileMovedToFolderPostPrcssng) {
		this.fileMovedToFolderPostPrcssng = fileMovedToFolderPostPrcssng;
	}
	
	

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	
	

	public long getFilePollerId() {
		return filePollerId;
	}

	public void setFilePollerId(long filePollerId) {
		this.filePollerId = filePollerId;
	}
	
	

	public String getErrorCategory() {
		return errorCategory;
	}

	public void setErrorCategory(String errorCategory) {
		this.errorCategory = errorCategory;
	}
	
	

	public String getReProcess() {
		return reProcess;
	}

	public void setReProcess(String reProcess) {
		this.reProcess = reProcess;
	}
	
	

	public String getProcessOrReprocess() {
		return processOrReprocess;
	}

	public void setProcessOrReprocess(String processOrReprocess) {
		this.processOrReprocess = processOrReprocess;
	}

	@Override
	public String toString() {
		return "KofaxDocumentData [id=" + id + ", batchClassId=" + batchClassId + ", docId=" + docId + ", barcode="
				+ barcode + ", priority=" + priority + ", shipmentTrackingNo=" + shipmentTrackingNo + ", batchDate="
				+ batchDate + ", batchTime=" + batchTime + ", batchGuid=" + batchGuid + ", fileNames="
				+ Arrays.toString(fileNames) + ", filePaths=" + Arrays.toString(filePaths) + ", errorCategory="
				+ errorCategory + ", errorDescription=" + errorDescription + ", status=" + status
				+ ", processedHostName=" + processedHostName + ", hasError=" + hasError
				+ ", fileMovedToFolderPostPrcssng=" + fileMovedToFolderPostPrcssng + ", sourceSystem=" + sourceSystem
				+ ", filePollerId=" + filePollerId + ", reProcess=" + reProcess + "]";
	}

	@Override
	public KofaxDocumentData mapRow(ResultSet rs, int rowNum) throws SQLException {
		KofaxDocumentData kofaxDocumentData = new KofaxDocumentData();
		kofaxDocumentData.setFilePollerId(rs.getLong("FILE_POLLER_ID"));
		kofaxDocumentData.setFileMovedToFolderPostPrcssng(rs.getString("FILE_PATH"));
		kofaxDocumentData.setBarcode(rs.getString("DCMNT_BAR_CD"));
		kofaxDocumentData.setShipmentTrackingNo(rs.getString("SHIPMENT_NUM"));
		kofaxDocumentData.setPriority("BAU");
		kofaxDocumentData.setReProcess(rs.getString("RE_PRCS"));
		String files[] = new String[1];
		String fileName = rs.getString("FILE_NM");
		files[0] = rs.getString("FILE_PATH")+"/"+fileName.substring(0, fileName.indexOf("."))+"/PRIMARY/"+fileName;
		kofaxDocumentData.setFilePaths(files);
		return kofaxDocumentData;
	}
	


}