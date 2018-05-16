package com.support.common.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.support.common.beans.DateSearch;
import com.support.common.beans.DumRowsFPDet;
import com.support.common.beans.FilePollerDetail;
import com.support.common.beans.StatusAndCount;
import com.support.common.beans.dumrows;
import com.support.common.dao.SmartdocsImagingDaoImpl;

@Controller
@RequestMapping("/imaging")
public class SmartdocsImagingController {

	
	@PostMapping(value="/GetImagingStatusForShipment", produces = "application/json")
	public @ResponseBody String getImagingStatus(@RequestBody FilePollerDetail fpDet, ModelMap model) {
		SmartdocsImagingDaoImpl smartdocsImagingDaoImpl = new SmartdocsImagingDaoImpl();
		List<StatusAndCount> statusAndCountList = smartdocsImagingDaoImpl.getImagingStatusForShipment(fpDet);
		
//		Map<String,String> statusMap = new HashMap<String,String>();
//		for(StatusAndCount statusAndCount:statusAndCountList){
//			statusMap.put(statusAndCount.getStatus(), statusAndCount.getCount()+"");
//		}
		dumrows r = new dumrows();
		r.setRows(statusAndCountList);
		Gson gson = new Gson();
		String jsn = gson.toJson(r);;
		return jsn;


	}
	
	@PostMapping(value = "/{sn}", produces = "application/json")
	public @ResponseBody String getImagingStatus(@PathVariable String sn, ModelMap model) {
		FilePollerDetail fpDet = new FilePollerDetail();
		fpDet.setShipmentNumber(sn);
		SmartdocsImagingDaoImpl smartdocsImagingDaoImpl = new SmartdocsImagingDaoImpl();
		List<StatusAndCount> statusAndCountList = smartdocsImagingDaoImpl.getImagingStatusForShipment(fpDet);
		
		dumrows r = new dumrows();
		r.setRows(statusAndCountList);
		Gson gson = new Gson();
		String jsn = gson.toJson(r);;
		return jsn;


	}	
	
	@PostMapping(value = "/detailsForStatus/{snst}", produces = "application/json")
	public @ResponseBody String getImagingDetailsForStatus(@PathVariable String snst,ModelMap model) {
		String snstArr[] = snst.split("-");
		FilePollerDetail fpDet = new FilePollerDetail();
		fpDet.setShipmentNumber(snstArr[0]);
		fpDet.setStatus(snstArr[1]);
		SmartdocsImagingDaoImpl smartdocsImagingDaoImpl = new SmartdocsImagingDaoImpl();
		List<FilePollerDetail> filePollerDetailList = smartdocsImagingDaoImpl.getImagingDetailsForStatus(fpDet);
		
		DumRowsFPDet r = new DumRowsFPDet();
		r.setRows(filePollerDetailList);
		Gson gson = new Gson();
		String jsn = gson.toJson(r);;
		return jsn;


	}
	
	@PostMapping(value = "/detailsForDuration/", produces = "application/json")
	public @ResponseBody String getImagingDetailsForDuration(@RequestBody DateSearch dateSearch) {
		
		FilePollerDetail fpDet = new FilePollerDetail();

		SmartdocsImagingDaoImpl smartdocsImagingDaoImpl = new SmartdocsImagingDaoImpl();
		List<FilePollerDetail> filePollerDetailList = smartdocsImagingDaoImpl.getImagingDetailsForDuration(dateSearch.getFromDate(),dateSearch.getToDate());
		
		DumRowsFPDet r = new DumRowsFPDet();
		r.setRows(filePollerDetailList);
		Gson gson = new Gson();
		String jsn = gson.toJson(r);;
		return jsn;

	}

	@PostMapping(value = "/detailsForDocument/{dcmnt}", produces = "application/json")
	public @ResponseBody String getImagingDetailsForDocument(@PathVariable String dcmnt,ModelMap model) {
		FilePollerDetail fpDet = new FilePollerDetail();
		fpDet.setDocumentBarcode(dcmnt);
		SmartdocsImagingDaoImpl smartdocsImagingDaoImpl = new SmartdocsImagingDaoImpl();
		List<FilePollerDetail> filePollerDetailList = smartdocsImagingDaoImpl.getImagingStatusForDocument(fpDet);
		
		DumRowsFPDet r = new DumRowsFPDet();
		r.setRows(filePollerDetailList);
		Gson gson = new Gson();
		String jsn = gson.toJson(r);;
		return jsn;

	}
	
}