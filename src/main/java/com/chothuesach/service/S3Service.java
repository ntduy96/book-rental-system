package com.chothuesach.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import java.io.InputStream;
import java.net.URI;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${jsa.s3.bucket}")
	private String bucketName;

	private final String SUFFIX = "/";

    public String uploadFile(String folderName, String file, InputStream inputStream) {
        String fileName = folderName + SUFFIX + file;
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, fileName));
        return s3Object.getObjectContent().getHttpRequest().getURI().toString();
    }

    public void deleteFile(URI uri) {
        s3Client.deleteObject(bucketName, uri.getPath().replaceFirst("/", ""));
    }

}
