package com.johndeere.adzone.admin.service.impl;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class FileHandlerService {

	@Value("${pi.s3.bucket}")
	private String awsS3AudioBucket;

	@Value("${pi.aws.secret_access_key}")
	private String secretKey;

	@Value("${pi.aws.access_key_id}")
	private String accessId;

	@Value("${pi.s3.region}")
	private String s3region;

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${target_s3_folder}")
	private String target_s3_folder; 
	
	public boolean uploadFileWithByteStream(ByteArrayInputStream file, String filename) {

		String target_folder = target_s3_folder.equals("") ? "" : target_s3_folder + "/";

		try {

			ObjectMetadata metadata = new ObjectMetadata();

			amazonS3.putObject(awsS3AudioBucket, target_folder + filename, file, metadata);

			return true;

		} catch (AmazonServiceException ase) {
			return false;

		} catch (AmazonClientException ace) {
			return false;
		} 
	}
	
	public String getTimeStamp() {
		Timestamp timestamp_obj = new Timestamp(System.currentTimeMillis());
		String timestamp = String.valueOf(timestamp_obj);
		timestamp = timestamp.replace(' ', '_');
		timestamp = timestamp.replace(":", "");

		return timestamp;
	}
	
	public String makeFileName(String filename, String timestamp) {
		String[] filenameObjs = filename.split("\\.");
		String[] modifiedFilenameArr = Arrays.copyOfRange(filenameObjs, 0, filenameObjs.length - 1);

		return String.join("", modifiedFilenameArr) + "_" + timestamp + "." + filenameObjs[filenameObjs.length - 1];
	}

}
