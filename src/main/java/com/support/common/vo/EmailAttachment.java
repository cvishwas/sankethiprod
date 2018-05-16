package com.support.common.vo;

import java.io.File;

public class EmailAttachment {
	
	private String fileName;
	private String contentType;
	private File fileContent;
	private String attatchmentId;
	private byte[] attachment;
	private String collateralCode;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the fileContent
	 */
	public File getFileContent() {
		return fileContent;
	}
	/**
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(File fileContent) {
		this.fileContent = fileContent;
	}
	/**
	 * @return the attatchmentId
	 */
	public String getAttatchmentId() {
		return attatchmentId;
	}
	/**
	 * @param attatchmentId the attatchmentId to set
	 */
	public void setAttatchmentId(String attatchmentId) {
		this.attatchmentId = attatchmentId;
	}
	/**
	 * @return the attachment
	 */
	public byte[] getAttachment() {
		return attachment;
	}
	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	/**
	 * @return the collateralCode
	 */
	public String getCollateralCode() {
		return collateralCode;
	}
	/**
	 * @param collateralCode the collateralCode to set
	 */
	public void setCollateralCode(String collateralCode) {
		this.collateralCode = collateralCode;
	}
	

}
