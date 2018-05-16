package com.support.common.dao;

import java.util.List;

import com.support.common.beans.FilePollerDetail;
import com.support.common.beans.StatusAndCount;

public interface SmartdocsImagingDao {
	public List<StatusAndCount> getImagingStatusForShipment(FilePollerDetail fpd);
	public List<FilePollerDetail> getImagingDetailsForStatus(FilePollerDetail fpd);
	public List<FilePollerDetail> getImagingStatusForDocument(FilePollerDetail fpd);
	public List<FilePollerDetail> getImagingDetailsForDuration(String fromDt,String toDt);

}
