package com.aws.ses.service;

import org.springframework.http.ResponseEntity;

import com.aws.ses.dto.BulkEmailDTO;
import com.aws.ses.dto.EmailDTO;

/**
 * @author Sumanth
 *
 */

public interface EmailService {

	ResponseEntity<Object> sendBulkMail(BulkEmailDTO bulkEmailDTO);

	ResponseEntity<Object> sendMail(EmailDTO emailDTO);

}
