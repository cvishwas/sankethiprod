package com.support.common.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.support.common.vo.EmailAttachment;
import com.support.common.vo.EmailDetails;



public class EmailUtil {
	private static final Logger logger = Logger.getLogger(EmailUtil.class);
	

	
	public static boolean sendEmail(EmailDetails emailDetails, List<EmailAttachment> emailAttachments) throws Exception {
		boolean isEmailSend = false;
		Map<String, Object> mailMap = new HashMap<String, Object>();
		if (null != emailDetails) {
			logger.info("Document sent from:" + emailDetails.getSender());
			mailMap.put("from", emailDetails.getSender());
			mailMap.put("cc", emailDetails.getCarbonCopy());

			logger.info("Document sent to:" + emailDetails.getRecipients());
			mailMap.put("to", emailDetails.getRecipients());
			mailMap.put("username", emailDetails.getUserName());
			mailMap.put("password", emailDetails.getPassword());
			mailMap.put("host", emailDetails.getHost());
			mailMap.put("port", emailDetails.getPort());
			mailMap.put("auth", emailDetails.getAuth());
			mailMap.put("starttls", emailDetails.getStarttlsEnable());
			mailMap.put("subject", emailDetails.getSubject());
			mailMap.put("content", emailDetails.getBody());
			mailMap.put("mailAttachment", emailAttachments);

			isEmailSend = MailUtils.sendEmail(mailMap);

			if (!isEmailSend) {
				throw new Exception("Error in sending email");
			}
		}
		return isEmailSend;
	}
	

}
