package com.aws.ses.service;

import org.springframework.http.ResponseEntity;

import com.aws.ses.dto.EmailTemplate;

/**
 * @author Sumanth
 *
 */

public interface EmailTemplateService {

	/**
	 * 
	 * @param emailTemplate
	 * @return 
	 */
	ResponseEntity<Object> create(EmailTemplate emailTemplate);

	/**
	 * 
	 * @param emailTemplate
	 * @return
	 */
	ResponseEntity<Object> update(EmailTemplate emailTemplate);

	/**
	 * 
	 * @param templateName
	 * @return
	 */
	ResponseEntity<Object> delete(String templateName);

	/**
	 * 
	 * @param templateName
	 * @return
	 */
	ResponseEntity<Object> getTemplate(String templateName);

}
