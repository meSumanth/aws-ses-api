package com.aws.ses;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@SpringBootApplication
@ComponentScan({"com.aws.ses.*"})
public class Application {
	
	@Value("${aws.ses.accesskeyid}")
	private String accessKeyId;
	
	@Value("${aws.ses.secretkey}")
	private String secretKey;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public AmazonSimpleEmailService getAmazonSimpleEmailService()
	{
		BasicAWSCredentials auth = new BasicAWSCredentials(accessKeyId, secretKey);
		AmazonSimpleEmailService amazonSimpleEmailService = AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(new StaticCredentialsProvider(auth))
				.withRegion(Regions.US_WEST_2).build();
		
		return amazonSimpleEmailService;
	}

}
