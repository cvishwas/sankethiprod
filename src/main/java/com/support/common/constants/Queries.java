package com.support.common.constants;

public class Queries {
	
	public static final String QUERY_TO_REPORT_FILE_POLLER = "select FILE_POLLER_ID,DCMNT_BAR_CD,NVL(SHIPMENT_NUM,' ')SHIPMENT_NUM,PRIORITY_CD,BTCH_DT,BTCH_GUID,"
			+ "FILE_NM,FILE_PATH,XML_FILE_NM,CR_DT,UPD_DT,PRCSD_STTS,NVL(ERROR, ' ')ERROR,RE_PRCS,RE_PRCS_DT,DEL_INDCTR,"
			+ "SRC_SYSTM, NVL(ERROR_CAT, ' ')ERROR_CAT from oondd.ndd_file_poller_dtl where to_char(upd_dt, 'YYYY') = to_char(sysdate, 'YYYY') "+
			"and to_char(UPD_DT, 'MM') = to_char(sysdate, 'MM') " +
			"and to_char(UPD_DT, 'DD') = to_char(sysdate, 'DD') and del_indctr = 'N'";
	
	public static final String AUTHENTICATE_USER = " ";
	
	public static final String CREATE_USER = 	"INSERT "+
												"INTO OONDD.NDD_USR_PRIV_MAP "+
												"  ( USR_ID, "+
												"   ROLE_CD, "+
												"   FIRST_NM, "+
												"   LAST_NM, "+
												"   ACCESS_TYPE, "+
												"   CLIENT_ID, "+
												"   CLIENT_NM, "+
												"   REGION, "+
												"   ENABLED, "+
												"   WRHS_CD, "+
												"   CR_BY, "+
												"   CR_DT, "+
												"   VLT_ID ) "+
												"  VALUES( "+
												"?, "+
												"?, "+
												"?, "+
												"?, "+
												"?, "+
												"?, "+
												"?, "+
												"?, "+
												"?, "+
												"?, "+
												"'SUPPORT', "+
												"sysdate, "+
												"? ) ";
	public static final String GET_USER_ROLES = "SELECT ROLE_CD FROM NDD_USR_PRIV_MAP WHERE USR_ID = ?";
	
	public static final String GET_USER_STATUS = "SELECT ENABLED FROM NDD_USR_PRIV_MAP WHERE USR_ID = ?";
	
	public static final String GET_USER_VLT = "SELECT VLT_ID FROM NDD_USR_PRIV_MAP WHERE USR_ID = ?";
	
	public static final String UPDATE_USER_ROLE = "UPDATE NDD_USR_PRIV_MAP SET ROLE_CD = ? WHERE USR_ID = ?";
	
	public static final String UPDATE_USER_ED = "UPDATE NDD_USR_PRIV_MAP SET ENABLED = ? WHERE USR_ID = ?";
	
	public static final String UPDATE_USER_VAULT = "UPDATE NDD_USR_PRIV_MAP SET VLT_ID = ? WHERE USR_ID = ?";
	
	public static final String GET_STATUS_FOR_SHIPMENT_SQL = "select shipment_num shipmentNum, PRCSD_STTS Status ,count(*) count from ndd_file_poller_dtl"
															+ " where shipment_num = ? group by PRCSD_STTS,shipment_num";
	
	public static final String GET_STATUS_FOR_DOCUMENT_SQL = "select FILE_POLLER_ID,DCMNT_BAR_CD,NVL(SHIPMENT_NUM,' ')SHIPMENT_NUM,PRIORITY_CD,BTCH_DT,BTCH_GUID,"
			+ "FILE_NM,FILE_PATH,XML_FILE_NM,CR_DT,UPD_DT,PRCSD_STTS,NVL(ERROR, ' ')ERROR,RE_PRCS,RE_PRCS_DT,DEL_INDCTR,"
			+ "SRC_SYSTM, NVL(ERROR_CAT, ' ')ERROR_CAT from oondd.ndd_file_poller_dtl where dcmnt_bar_cd = ? ";
	
	public static final String QUERY_TO_REPORT_FILE_POLLER_ON_STATUS_AND_SHIPMENT = "select FILE_POLLER_ID,DCMNT_BAR_CD,NVL(SHIPMENT_NUM,' ')SHIPMENT_NUM,PRIORITY_CD,BTCH_DT,BTCH_GUID,"
			+ "FILE_NM,FILE_PATH,XML_FILE_NM,CR_DT,UPD_DT,PRCSD_STTS,NVL(ERROR, ' ')ERROR,RE_PRCS,RE_PRCS_DT,DEL_INDCTR,"
			+ "SRC_SYSTM, NVL(ERROR_CAT, ' ')ERROR_CAT from oondd.ndd_file_poller_dtl where PRCSD_STTS = ? and SHIPMENT_NUM = ?";	
	
	public static final String QUERY_TO_REPORT_FILE_POLLER_ON_DURATION = "select FILE_POLLER_ID,DCMNT_BAR_CD,NVL(SHIPMENT_NUM,' ')SHIPMENT_NUM,PRIORITY_CD,BTCH_DT,BTCH_GUID,"
			+ "FILE_NM,FILE_PATH,XML_FILE_NM,CR_DT,UPD_DT,PRCSD_STTS,NVL(ERROR, ' ')ERROR,RE_PRCS,RE_PRCS_DT,DEL_INDCTR,"
			+ "SRC_SYSTM, NVL(ERROR_CAT, ' ')ERROR_CAT from oondd.ndd_file_poller_dtl where UPD_DT > ? and UPD_DT < ?";


}
