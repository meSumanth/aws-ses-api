package com.aws.ses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ses.dto.BulkEmailDTO;
import com.aws.ses.dto.EmailDTO;
import com.aws.ses.service.EmailService;

/**
 * @author Sumanth
 *
 */

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendmail")
	public ResponseEntity<Object> sendMail(@RequestBody EmailDTO emailDTO)
	{
		return emailService.sendMail(emailDTO);
	}
	
	@PostMapping("/bulkmail")
	public ResponseEntity<Object> sendBulkMail(@RequestBody BulkEmailDTO bulkEmailDTO)
	{
		return emailService.sendBulkMail(bulkEmailDTO);
	}

}
