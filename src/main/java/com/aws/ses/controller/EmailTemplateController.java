package com.aws.ses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ses.dto.EmailTemplate;
import com.aws.ses.service.EmailTemplateService;

/**
 * @author Sumanth
 *
 */

@RestController
@RequestMapping("/templates")
public class EmailTemplateController {
	
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createTemplate(@RequestBody EmailTemplate emailTemplate)
	{
		return emailTemplateService.create(emailTemplate);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateTemplate(@RequestBody EmailTemplate emailTemplate)
	{
		emailTemplateService.update(emailTemplate);
		return null;
	}
	
	@DeleteMapping("/delete/{templateName}")
	public ResponseEntity<Object> deleteTemplate(@PathVariable String templateName)
	{
		return emailTemplateService.delete(templateName);
	}
	
	@GetMapping("/{templateName}")
	public ResponseEntity<Object> getTemplate(@PathVariable String templateName)
	{
		return emailTemplateService.getTemplate(templateName);
	}

}
