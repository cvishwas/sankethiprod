package com.support.common.dao;

import java.util.List;

import com.support.common.beans.FilePollerDetail;


public interface FilePollerDao {
	public List<FilePollerDetail> getFilePollerDetail(String sourceSystem);
}
