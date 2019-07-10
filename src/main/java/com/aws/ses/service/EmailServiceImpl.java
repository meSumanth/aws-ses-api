package com.aws.ses.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.BulkEmailDestination;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendBulkTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.SendBulkTemplatedEmailResult;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailResult;
import com.aws.ses.dto.BulkEmailDTO;
import com.aws.ses.dto.EmailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sumanth
 *
 */

@Service
public class EmailServiceImpl implements EmailService{
	
	public static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private AmazonSimpleEmailService amazonSimpleEmailService;

	@Override
	public ResponseEntity<Object> sendMail(EmailDTO emailDTO) {
		try 
		{
			LOG.info("Sending email payload is : {},", emailDTO);
			
			SendTemplatedEmailRequest emailRequest = new SendTemplatedEmailRequest();
			emailRequest.setTemplate(emailDTO.getTemplateName());
			emailRequest.setSource(emailDTO.getFrom());//TODO
			
			Destination destination = new Destination();
			destination.setToAddresses(Arrays.asList(emailDTO.getTo()));
			destination.setBccAddresses(Arrays.asList(emailDTO.getBcc()));
			destination.setCcAddresses(Arrays.asList(emailDTO.getCc()));
			
			emailRequest.setDestination(destination);
			emailRequest.setTemplateData(new ObjectMapper().writeValueAsString(emailDTO.getData()));
			
			SendTemplatedEmailResult response = amazonSimpleEmailService.sendTemplatedEmail(emailRequest);
			LOG.info("Mail has been sent successfully.");
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getSdkHttpMetadata().getHttpStatusCode()));
			
		} catch (Exception e) {
			LOG.error("Exception occurred while sending mail, exception: {}", e);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object> sendBulkMail(BulkEmailDTO bulkEmailDTO) {
		try 
		{
			LOG.info("Sending email payload is : {},", bulkEmailDTO);
			
			SendBulkTemplatedEmailRequest emailRequest = new SendBulkTemplatedEmailRequest();
			emailRequest.setTemplate(bulkEmailDTO.getTemplateName());
			emailRequest.setSource(bulkEmailDTO.getFrom());//TODO
			
			BulkEmailDestination destination = null;
			List<BulkEmailDestination> destinations = new ArrayList<>();
			for (String to : bulkEmailDTO.getTo()) {
				destination = new BulkEmailDestination();
				destination.setDestination(new Destination(Arrays.asList(to)));
				destination.setReplacementTemplateData(new ObjectMapper().writeValueAsString(bulkEmailDTO.getData()));
				
				destinations.add(destination);
			}
			
			emailRequest.setDestinations(destinations);
			emailRequest.setDefaultTemplateData("{\"name\":\"Sachin\"}");
			
			SendBulkTemplatedEmailResult response = amazonSimpleEmailService.sendBulkTemplatedEmail(emailRequest);
			LOG.info("Bulk Mail has been sent successfully.", response.getSdkHttpMetadata());
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getSdkHttpMetadata().getHttpStatusCode()));
			
		} catch (Exception e) {
			LOG.error("Exception occurred while sending mail, exception: {}", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
