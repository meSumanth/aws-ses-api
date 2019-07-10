package com.aws.ses.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.CreateTemplateRequest;
import com.amazonaws.services.simpleemail.model.CreateTemplateResult;
import com.amazonaws.services.simpleemail.model.DeleteTemplateRequest;
import com.amazonaws.services.simpleemail.model.DeleteTemplateResult;
import com.amazonaws.services.simpleemail.model.GetTemplateRequest;
import com.amazonaws.services.simpleemail.model.GetTemplateResult;
import com.amazonaws.services.simpleemail.model.Template;
import com.amazonaws.services.simpleemail.model.UpdateTemplateRequest;
import com.amazonaws.services.simpleemail.model.UpdateTemplateResult;
import com.aws.ses.dto.EmailTemplate;

/**
 * @author Sumanth
 *
 */

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService{
	
	public static final Logger LOG = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);
	
	@Autowired
	private AmazonSimpleEmailService amazonSimpleEmailService;

	@Override
	public ResponseEntity<Object> create(EmailTemplate emailTemplate) {
		
		try
		{
			LOG.info("Creating email template with payload: {}", emailTemplate);
			
			CreateTemplateRequest templateReq = new CreateTemplateRequest();
			Template template = new Template();
			BeanUtils.copyProperties(emailTemplate, template);
			templateReq.setTemplate(template);
			
			CreateTemplateResult response = amazonSimpleEmailService.createTemplate(templateReq);
			LOG.info("Email template has been created successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			LOG.error("Eception occurred while creating the template. exception: {}", e);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> update(EmailTemplate emailTemplate) {
		try
		{
			LOG.info("Updating email template with payload: {}", emailTemplate);
			
			UpdateTemplateRequest updateTemplateRequest = new UpdateTemplateRequest();
			Template template = new Template();
			BeanUtils.copyProperties(emailTemplate, template);
			updateTemplateRequest.setTemplate(template);
			
			UpdateTemplateResult response = amazonSimpleEmailService.updateTemplate(updateTemplateRequest);
			LOG.info("Email template has been updated successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			LOG.error("Eception occurred while updating the template. exception: {}", e);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> delete(String templateName) {
		try
		{
			LOG.info("Deleting email template :{}", templateName);
			
			DeleteTemplateRequest deleteTemplateRequest = new DeleteTemplateRequest();
			deleteTemplateRequest.setTemplateName(templateName);
			
			DeleteTemplateResult response = amazonSimpleEmailService.deleteTemplate(deleteTemplateRequest);
			LOG.info("Email template has been deleted successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			LOG.error("Eception occurred while deleting the template. exception: {}", e);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> getTemplate(String templateName) {
		try
		{
			LOG.info("Fetching email template :{}", templateName);
			
			GetTemplateRequest templateRequest = new GetTemplateRequest();
			templateRequest.setTemplateName(templateName);
			
			GetTemplateResult response = amazonSimpleEmailService.getTemplate(templateRequest);
			LOG.info("Email template has been fetched successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			LOG.error("Eception occurred while fetching the template. exception: {}", e);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
