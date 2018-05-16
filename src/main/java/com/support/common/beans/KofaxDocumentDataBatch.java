package com.support.common.beans;

import java.util.ArrayList;
import java.util.List;

public class KofaxDocumentDataBatch {
	private boolean errorInBatch;
	private List<KofaxDocumentData> kofaxDocumentData = new ArrayList<KofaxDocumentData>();
	private String errorAtBatchLevel = "";
	private String xmlFileName = "";
	
	public boolean isErrorInBatch() {
		return errorInBatch;
	}
	public void setErrorInBatch(boolean errorInBatch) {
		this.errorInBatch = errorInBatch;
	}
	
	public List<KofaxDocumentData> getKofaxDocumentData() {
		return kofaxDocumentData;
	}
	public void setKofaxDocumentData(List<KofaxDocumentData> kofaxDocumentData) {
		this.kofaxDocumentData = kofaxDocumentData;
	}
	
	
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	public String getErrorAtBatchLevel() {
		return errorAtBatchLevel;
	}
	public void setErrorAtBatchLevel(String errorAtBatchLevel) {
		this.errorAtBatchLevel = errorAtBatchLevel;
	}
	@Override
	public String toString() {
		return "KofaxDocumentDataBatch [errorInBatch=" + errorInBatch + ", kofaxDocumentData=" + kofaxDocumentData
				+ ", errorAtBatchLevel=" + errorAtBatchLevel + ", xmlFileName=" + xmlFileName + "]";
	}
	
	
}