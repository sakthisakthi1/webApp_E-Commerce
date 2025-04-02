package com.johndeere.adzone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	@Value("${pi.aws.access_key_id}")
	private String awsId;

	@Value("${pi.aws.secret_access_key}")
	private String awsKey;

	@Value("${pi.s3.region}")
	private String region;

	@Bean
	public AmazonS3 s3client() {

		ClientConfiguration clientConfiguration = new ClientConfiguration().withMaxErrorRetry(3)
				.withConnectionTimeout(10 * 60 * 1000).withSocketTimeout(10 * 60 * 1000);

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withClientConfiguration(clientConfiguration).build();

		return s3Client;
	}
}
