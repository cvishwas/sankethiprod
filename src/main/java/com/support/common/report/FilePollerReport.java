package com.support.common.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bnymellon.util.PropertyLoader;
import com.support.common.beans.FilePollerDetail;
import com.support.common.beans.KofaxDocumentData;
import com.support.common.beans.KofaxDocumentDataBatch;
import com.support.common.constants.ErrorCategoryEnum;
import com.support.common.constants.Vars;
import com.support.common.dao.FilePollerDaoImpl;
import com.support.common.util.EmailUtil;
import com.support.common.vo.EmailAttachment;
import com.support.common.vo.EmailDetails;


public class FilePollerReport {
	private static final Logger logger = Logger.getLogger(FilePollerReport.class);
	private static DataSource dataSource;
	@RequestMapping(method = RequestMethod.GET, value = "/generateEODReport")
	public static void generateEODReport(DataSource ds){
		dataSource = ds;
		PropertyLoader propertyLoader = new PropertyLoader(dataSource);
		System.out.println("In generateEODReport");
		List<FilePollerDetail> fpListAll = null;
		List<FilePollerDetail> fpListInbound = null;
		List<FilePollerDetail> fpListOutbound = null;
		EmailDetails emailDetails = new EmailDetails();
		StringBuilder sbEmailbody = new StringBuilder();
		
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		
		String environmentProperty = System.getProperty("config");
		if (StringUtils.isBlank(environmentProperty))
			environmentProperty = "Dev";
		
		try{
			System.out.println("Before getting data from DB");
			fpListAll  = reportFromDb();
			System.out.println("fpListAll size -> "+fpListAll.size());
			double errorCountAll = getErrorCountForStatus(fpListAll,Vars.FAILURE);
			System.out.println("errorCountAll -> "+errorCountAll);
			double successCountAll = getErrorCountForStatus(fpListAll,Vars.SUCCESS);
			System.out.println("successCountAll -> "+successCountAll);
			float rateOfFailureAll = 0;
			if(fpListAll.size() > 0){
				rateOfFailureAll = (float)(errorCountAll * 100)/(fpListAll.size());
				System.out.println("rateOfFailureAll -> "+rateOfFailureAll);
			}
			
			float rateOfFailureInbound = 0 ;
			fpListInbound  = getReportForSource("Inbound");
			System.out.println("fpListInbound size -> "+fpListInbound.size());
//			fpListOutbound  = getReportForSource(dBUtility,"Outbound");
			double errorCountInbound = getErrorCountForStatus(fpListInbound,Vars.FAILURE);
			System.out.println("errorCountInbound -> "+errorCountInbound);
			double successCountInbound = getErrorCountForStatus(fpListInbound,Vars.SUCCESS);	
			System.out.println("successCountInbound -> "+successCountInbound);
			if(fpListInbound.size() > 0){
				rateOfFailureInbound = (float)(errorCountInbound/(fpListInbound.size()))*100;
				System.out.println("rateOfFailureInbound -> "+rateOfFailureInbound);
			}
			
			System.out.println("Start creating the html");
			
			sbEmailbody.append("<h2 style=\"color: #5e9ca0;\"><span style=\"color: #333399;\">"+environmentProperty+" Imaging Failures for "+dateFormat.format(date) +"</span></h2>");
			sbEmailbody.append("<h2 style=\"color: #2e6c80;\"><span style=\"color: #333399;\">Summary:</span></h2>");
			sbEmailbody.append("<h3><span style=\"color: #333399;\">For all documents:</span></h3>");
			sbEmailbody.append("<table style=\"height: 94px;\" width=\"286\">");
			sbEmailbody.append("<tbody><tr><td style=\"background-color: #00ccff; text-align: right;\"></td><td style=\"background-color: #00ccff; text-align: center;\"><strong>All</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #00ccff; text-align: center;\"><strong>Inbound</strong></td>");			
			sbEmailbody.append("<tbody><tr><td style=\"background-color: #00ccff; text-align: right;\"><strong>Total Scanned</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+fpListAll.size()+"</td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+fpListInbound.size()+"</td></tr>");
			sbEmailbody.append("<tr><td style=\"background-color: #00ccff; text-align: right;\"><strong>Failed</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+errorCountAll+"</td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+errorCountInbound+"</td>");
			sbEmailbody.append("</tr><tr><td style=\"background-color: #00ccff; text-align: right;\"><strong>Passed</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+successCountAll+"</td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+successCountInbound+"</td>");
			sbEmailbody.append("</tr><tr><td style=\"background-color: #00ccff; text-align: right;\"><strong>Failure Rate</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+numberFormat.format(rateOfFailureAll)+"%</td>");
			sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+numberFormat.format(rateOfFailureInbound)+"%</td>");
			sbEmailbody.append("</tr></tbody></table><td style=\"background-color: #F2F3F4; text-align: center;\">&nbsp;</td></tr></tbody></table>");
			
			
			sbEmailbody.append("<h3><span style=\"color: #333399;\">Failures By Reason(For Inbound)</span></h3>");
			sbEmailbody.append("<table style=\"height: 55px;\" width=\"538\">");
			sbEmailbody.append("<tbody><tr><td style=\"background-color: #00ccff; text-align: center;\"><strong>Error Reason</strong></td>"
					+ "<td style=\"background-color: #00ccff; text-align: center;\"><strong>Count of Docs</strong></td><td style=\"background-color: #00ccff;"
					+ " text-align: center;\"><strong>%</strong></td></tr>");
			

			HashMap<String,String> hmErrorCat = new HashMap<String,String>();
			for (ErrorCategoryEnum errCatMstr : ErrorCategoryEnum.values()) {
				int cnt = 0;
				for (FilePollerDetail filePollerDetail : fpListInbound) {
					if(filePollerDetail.getStatus().equals(Vars.FAILURE)){
						System.out.println(filePollerDetail.getDocumentBarcode() +" -> Error Categories: "+filePollerDetail.getError_cat());
						String errCat[] = filePollerDetail.getError_cat().split("#");
						if (errCat[1].equals(errCatMstr.name())) {
							cnt++;
						}
					}

				}
				if(cnt > 0){
					float pct = (float)((cnt*100)/errorCountInbound);

					sbEmailbody.append("<tr><td style=\"width: 172px; text-align: center;\">"+errCatMstr.name()+"</td>");
					sbEmailbody.append("<td style=\"width: 172px; text-align: center;\">"+cnt+"</td>");
					sbEmailbody.append("<td style=\"width: 172px; text-align: center;\">"+numberFormat.format(pct)+"</td></tr>");
				}

			}			
			

			sbEmailbody.append("<tr><td style=\"background-color: #F2F3F4; text-align: center;\"><strong>Grand Total</strong></td>"
					+ "<td style=\"background-color: #F2F3F4; text-align: center;\"><strong>"+errorCountInbound+"</strong></td>"
					+ "<td style=\"background-color: #F2F3F4; text-align: center;\"><strong></strong></td></tr>");
			sbEmailbody.append("</tbody></table><p>&nbsp;</p>");			
			


			sbEmailbody.append("<h3><span style=\"color: #333399;\">Failures By Shipment Number(For Inbound)</span></h3>"
					+ "<table style=\"height: 55px;\" width=\"538\"><tbody><tr><td style=\"background-color: #00ccff; text-align: center;\">"
					+ "<strong>Shipment Number</strong></td><td style=\"background-color: #00ccff; text-align: center;\">"
					+ "<strong>Processed</strong></td><td style=\"background-color: #00ccff; text-align: center;\">"
					+ "<strong>Failure</strong></td> <td style=\"background-color: #00ccff; text-align: center;\"><strong>Total</strong></td></tr>");
			
			
			
			HashSet<String> hsShipment = new HashSet<String>();
			for(FilePollerDetail filePollerDetail:fpListInbound){
				hsShipment.add(filePollerDetail.getShipmentNumber());
			}
				int grandSuccTot = 0;
				int grandFailTot = 0;
			for(String shipmentNum:hsShipment){
				int failcount = 0;
				int succCount = 0;
				int tot = 0;
				for(FilePollerDetail filePollerDetail:fpListInbound){
					if(filePollerDetail.getShipmentNumber().equals(shipmentNum)){
						if(filePollerDetail.getStatus().equalsIgnoreCase(Vars.FAILURE)){
							failcount++;
						}else{
							succCount++;
						}
						
					}

				}	
				tot = failcount + succCount;
				grandSuccTot = grandSuccTot + succCount;
				grandFailTot = grandFailTot + failcount;
				sbEmailbody.append("<tr><td style=\"width: 172px; text-align: center;\">"+shipmentNum+
						"</td><td style=\"width: 172px; text-align: center;\">"+succCount+"</td>"
						+ "<td style=\"width: 172px; text-align: center;\">"+failcount+"</td>"
						+ "<td style=\"width: 172px; text-align: center;\">"+tot+"</td></tr>");
				
				
			}
			sbEmailbody.append("<tr><td style=\"background-color: #F2F3F4; text-align: center;\"><strong>Grand Total</strong></td>"
					+ "<td style=\"background-color: #F2F3F4; text-align: center;\"><strong>"+grandSuccTot+"</strong></td>"
							+ "<td style=\"background-color: #F2F3F4; text-align: center;\"><strong>"+grandFailTot+"</strong></td>"
									+ "<td style=\"background-color: #F2F3F4; text-align: center;\"><strong>"+fpListInbound.size()+"</strong></td></tr>");
			sbEmailbody.append("</tbody></table><p>&nbsp;</p>");
			
			System.out.println("Preparing for email");
			File reportDetailFile = getReportDetails(fpListAll);
			EmailAttachment emailAttachment = new EmailAttachment();
			emailAttachment.setContentType("text/html");
			emailAttachment.setFileContent(reportDetailFile);
			emailAttachment.setFileName(reportDetailFile.getName());
			emailAttachment.setAttachment(Files.readAllBytes(reportDetailFile.toPath()));
			List<EmailAttachment> emailAttachmentList = new ArrayList<EmailAttachment>(); 
			emailAttachmentList.add(emailAttachment);
			
			emailDetails.setRecipients(PropertyLoader.getPropertyValue("smartdocs.kofax.support.email.to"));
			emailDetails.setEmailAttachments(emailAttachmentList);
			emailDetails.setHasEnoughInfoToSendEmail(true);
			emailDetails.setSender("NDDINFRA@bnymellon.com");
			emailDetails.setSubject(environmentProperty+" Imaging Failure for "+dateFormat.format(date));
			emailDetails.setBody(sbEmailbody.toString());
			emailDetails.setAuth(PropertyLoader.getPropertyValue("smtp.auth"));
			emailDetails.setHost(PropertyLoader.getPropertyValue("smtp.host"));
			emailDetails.setPassword(PropertyLoader.getPropertyValue("smtp.password"));
			emailDetails.setPort(PropertyLoader.getPropertyValue("smtp.port"));
			emailDetails.setUserName(PropertyLoader.getPropertyValue("smtp.username"));
			emailDetails.setCarbonCopy(PropertyLoader.getPropertyValue("smtp.email.cc"));
			emailDetails.setStarttlsEnable(PropertyLoader.getPropertyValue("smtp.starttls.enable"));
			System.out.println("Before sending email");
			EmailUtil.sendEmail(emailDetails, emailAttachmentList);
			System.out.println("Email sent");
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	private static File getReportDetails(List<FilePollerDetail> filePollerDetailList){
		File fRepDet = new File("RepDet.html");
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		StringBuilder sbEmailbody = new StringBuilder();	
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try{
			fw = new FileWriter(fRepDet);
			bw = new BufferedWriter(fw);
			sbEmailbody.append("<html><h3><span style=\"color: #333399;\">Scan details for all documents:</span></h3>");
			sbEmailbody.append("<table style=\"height: 94px;width: 1000px\">");
			sbEmailbody.append("<tbody><tr><td style=\"background-color: #00ccff; text-align: center;\">Shipment Number</td><td style=\"background-color: #00ccff; text-align: center;\"><strong>Document Barcode</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #00ccff; text-align: center;\"><strong>Source System</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #00ccff; text-align: center;\"><strong>Priority</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #00ccff; text-align: center;\"><strong>Batch Date/Time</strong></td>");				
			sbEmailbody.append("<td style=\"background-color: #00ccff; text-align: center;\"><strong>Status</strong></td>");
			sbEmailbody.append("<td style=\"background-color: #00ccff; text-align: center;\"><strong>Failure Category</strong></td>");				
			sbEmailbody.append("<td style=\"background-color: #00ccff; text-align: center;\"><strong>Failure Reason</strong></td>");
			sbEmailbody.append("<tbody>");
			for(FilePollerDetail filePollerDetail:filePollerDetailList){
				sbEmailbody.append("<tr><td style=\"background-color: #00ccff; text-align: center;\">"+filePollerDetail.getShipmentNumber()+"</td>");
				sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+filePollerDetail.getDocumentBarcode()+"</td>");
				sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+filePollerDetail.getSourceSystem()+"</td>");
				sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+filePollerDetail.getPriority_cd()+"</td>");
				sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+df.format(filePollerDetail.getBatchDate())+"</td>");
				sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+filePollerDetail.getStatus()+"</td>");
				sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+filePollerDetail.getError_cat()+"</td>");
				sbEmailbody.append("<td style=\"background-color: #F2F3F4; text-align: center;\">"+filePollerDetail.getError()+"</td>");
				sbEmailbody.append("</tr>");
			}
			sbEmailbody.append("</tbody></table></html>");

			bw.write(sbEmailbody.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return fRepDet;
	}

	
	private static EmailDetails getEmailDetails(String xmlFileName, Object javaObject) {

		KofaxDocumentDataBatch kofaxDocumentDataBatch = (KofaxDocumentDataBatch)javaObject;
		List<KofaxDocumentData> kofaxDocumentDataList = kofaxDocumentDataBatch.getKofaxDocumentData();
		EmailDetails emailDetails = new EmailDetails();
		StringBuilder sbEmailbody = new StringBuilder();
		sbEmailbody.append("<H3>File poller error for "+xmlFileName +"</H3>");
		if(kofaxDocumentDataBatch.isErrorInBatch()){
			emailDetails.setHasEnoughInfoToSendEmail(true);
			sbEmailbody.append("<br>");
			sbEmailbody.append("Error at XML Level<br>");
			sbEmailbody.append(kofaxDocumentDataBatch.getErrorAtBatchLevel());
			sbEmailbody.append("<BR>");
			
		}
		for(KofaxDocumentData KofaxDocumentData:kofaxDocumentDataList){
			if(KofaxDocumentData.isHasError()){
				emailDetails.setHasEnoughInfoToSendEmail(true);
				sbEmailbody.append("<br>");
				sbEmailbody.append("<u>Doc Id = "+KofaxDocumentData.getDocId()+"</u>").append("<br>");
				if(StringUtils.isNotBlank(KofaxDocumentData.getErrorDescription())){
					sbEmailbody.append("Error Description: "+KofaxDocumentData.getErrorDescription()).append("<br>");
				}
			}
		}
		
		String environmentProperty = System.getProperty("config");
		if (StringUtils.isBlank(environmentProperty))
			environmentProperty = "Dev";		
		
		
		emailDetails.setSender("NDDINFRA@bnymellon.com");
		emailDetails.setSubject("File Poller Error for " + xmlFileName +" in "+environmentProperty);
		emailDetails.setBody(sbEmailbody.toString());
		emailDetails.setAuth(PropertyLoader.getPropertyValue("smtp.auth"));
		emailDetails.setHost(PropertyLoader.getPropertyValue("smtp.host"));
		emailDetails.setPassword(PropertyLoader.getPropertyValue("smtp.password"));
		emailDetails.setPort(PropertyLoader.getPropertyValue("smtp.port"));
		emailDetails.setUserName(PropertyLoader.getPropertyValue("smtp.username"));
		emailDetails.setCarbonCopy(PropertyLoader.getPropertyValue("smtp.email.cc"));
		emailDetails.setStarttlsEnable(PropertyLoader.getPropertyValue("smtp.starttls.enable"));
		return emailDetails;
	}	
	
	public static List<FilePollerDetail> reportFromDb(){

		List<FilePollerDetail> fpList = null;
		FilePollerDaoImpl filePollerDaoImpl = new FilePollerDaoImpl();
		try{
			fpList = filePollerDaoImpl.getFilePollerDetail("ALL");
			System.out.println("Total number of documents scanned today:"+fpList.size());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return fpList;
		
	}
	
	public static List<FilePollerDetail> getReportForSource(String sourceSystem){

		List<FilePollerDetail> fpList = null;
		FilePollerDaoImpl filePollerDaoImpl = new FilePollerDaoImpl();
		try{
			fpList = filePollerDaoImpl.getFilePollerDetail(sourceSystem);
			System.out.println("Total number of documents scanned today:"+fpList.size());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return fpList;
		
	}
	
	public static int getErrorCountForStatus(List<FilePollerDetail> fpList,String status){
		int errorCount = 0;
		for(FilePollerDetail filePollerDetail:fpList){
			String fpStts = filePollerDetail.getStatus();
			if(!StringUtils.isEmpty(fpStts)){
				if(filePollerDetail.getStatus().equalsIgnoreCase(status)){
					errorCount++;
				}
			}
		}
		return errorCount++;
	}
	
//	public static void main(String args[]){
//		FilePollerReport fpr = new FilePollerReport();
//		fpr.generateEODReport();
//	}
}
