package com.example.demo.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

import jakarta.annotation.*;
import jakarta.servlet.*;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.*;
import software.amazon.awssdk.services.s3.*;

@Configuration
public class CustomConfiguration {

	@Value("${aws.accessKeyId}")
	private String accessKeyId;
	@Value("${aws.secretAccessKey}")
	private String secretAccessKey;
	
	@Autowired
	private ServletContext application;
	
	@Value("${aws.bucketUrl}")
	private String bucketUrl;
	
	@PostConstruct
	public void init() {
		application.setAttribute("bucketUrl",bucketUrl);
	}
	
	@Bean
	public S3Client s3client() {
		AwsBasicCredentials credentials
		= AwsBasicCredentials.create(accessKeyId, secretAccessKey);
		AwsCredentialsProvider provider = StaticCredentialsProvider.create(credentials);
		
		S3Client s3client = S3Client.builder()
				.credentialsProvider(provider)
				.region(Region.AP_NORTHEAST_2)
				.build();
		
		return s3client;
	}
}