package com.support.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.NDC;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.support.common.beans.KofaxDocumentData;



public class FPReporting {
	
	public void readProcessedFiles(String processedDir, String fileType, String opFileName){
		File f = new File(processedDir);
		File[] xmlFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(fileType);
			}
		});		
	    StringBuilder sb = new StringBuilder();
		for(File fBuff:xmlFiles){
			System.out.println("-------"+fBuff.getAbsolutePath());

		    try(FileInputStream inputStream = new FileInputStream(fBuff.getAbsolutePath())) {     
		        String everything = IOUtils.toString(inputStream);
		        sb.append(everything);
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	    String everything = sb.toString();
	    
	    BufferedWriter bw = null;
		FileWriter fw = null;

		try {


			fw = new FileWriter("C:/dump/"+opFileName);
			bw = new BufferedWriter(fw);
			bw.write(everything);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}	    
	    
	    System.out.println(everything);
	}
	
	public void readXMLFiles(String directoryToScan){
		File workingDirectory = new File(directoryToScan);
		File[] xmlFiles = workingDirectory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith("xml");
			}
		});

		if (xmlFiles != null && xmlFiles.length > 0) {
			long startTime = System.currentTimeMillis();
		    BufferedWriter bw = null;
			FileWriter fw = null;
			try {
				fw = new FileWriter("C:/dump/prXML.txt");
				bw = new BufferedWriter(fw);
				for (File xmlFile : xmlFiles) {
					String s = processXml(xmlFile,bw);
					bw.write(s);
				}				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}	 

			


		}
}
	
	private File getDtdFile(File xmlFile) {
		String dtdFileName = null;
		try {
			if (xmlFile.exists()) {
				dtdFileName = xmlFile.getPath().replace(xmlFile.getName().substring(xmlFile.getName().indexOf(".")),
						".dtd");
				File dtdFile = new File(dtdFileName);
				if (dtdFile.exists())
					return dtdFile;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String processXml(File xmlFile,BufferedWriter bw) {

		StringBuilder sb = new StringBuilder();

		try {
			
			List<KofaxDocumentData> kofaxDocumentDataList = getDocumentData(xmlFile);
			for(KofaxDocumentData kofaxDocumentData:kofaxDocumentDataList){
				sb.append(kofaxDocumentData.getBarcode()+":"+kofaxDocumentData.getShipmentTrackingNo());
				sb.append(System.lineSeparator());
			}

		} catch (Exception ex) {
					ex.printStackTrace();
		}  
		return sb.toString();
	}
	
	public static List<KofaxDocumentData> getDocumentData(final File xmFile) throws Exception {
		List<KofaxDocumentData> docList = new ArrayList<>();
		try {
			XmlParser xmlParser = new XmlParser();
			Document document = xmlParser.getDocument(xmFile);

			NodeList nodeList = xmlParser.getNodeList(document, KofaxDocumentData.DOCUMENT_XPATH);
			if (nodeList != null) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					KofaxDocumentData docData = new KofaxDocumentData();
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						String docId = element.getAttribute(KofaxDocumentData.DOCUMENT_ID_TAG);
						docData.setDocId(docId);

						NodeList docFieldNodeList = element.getElementsByTagName(KofaxDocumentData.DOCUMENTFIELD_TAG);
						for (int j = 0; j < docFieldNodeList.getLength(); j++) {
							Element docFieldElemement = (Element) docFieldNodeList.item(j);
							String docFieldName = docFieldElemement.getAttribute("NAME");
							String value = docFieldElemement.getAttribute("VALUE");
							if (KofaxDocumentData.BARCODE_TAG.equalsIgnoreCase(docFieldName))
								docData.setBarcode(value);
							else if (KofaxDocumentData.PRIORITY.equalsIgnoreCase(docFieldName))
								docData.setPriority(value);
							else if (KofaxDocumentData.SHIPMENT_TRACKING_NUMBER.equalsIgnoreCase(docFieldName))
								docData.setShipmentTrackingNo(value);
							else if (KofaxDocumentData.BATCH_DATE.equalsIgnoreCase(docFieldName))
								docData.setBatchDate(value);
							else if (KofaxDocumentData.BATCH_TIME.equalsIgnoreCase(docFieldName))
								docData.setBatchTime(value);
							else if (KofaxDocumentData.BATCH_GUID.equalsIgnoreCase(docFieldName))
								docData.setBatchGuid(value);

							NodeList primaryFileNodeList = element
									.getElementsByTagName(KofaxDocumentData.PRIMARY_FILENAME_TAG);

							
						}
						docList.add(docData);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return docList;
	}

	

	public static void main(String args[]){
		FPReporting fPReporting = new FPReporting();
//		fPReporting.readProcessedFiles("C:/dump/prod/pf","processed","prcsd.txt");
//		fPReporting.readXMLFiles("C:/dump/prod/Jan");
		fPReporting.readProcessedFiles("C:/dump/errors","fail","prcsdErrors.txt");
	}
}
