package com.chothuesach.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import java.io.InputStream;
import java.net.URI;

/**
 * Configuration for AWS S3
 */
public class AwsS3Config {

    private static final String bucketName = "book-rental-system-files";

    private static final AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();

    private static final AmazonS3 s3client = new AmazonS3Client(credentials);

    private static final String SUFFIX = "/";

    public AmazonS3 getS3client() {
        return s3client;
    }

    public static String uploadFile(String folderName, String file, InputStream inputStream) {
        String fileName = folderName + SUFFIX + file;
        s3client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
        S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, fileName));
        return s3Object.getObjectContent().getHttpRequest().getURI().toString();
    }

    public static void deleteFile(URI uri) {
        s3client.deleteObject(bucketName, uri.getPath().replaceFirst("/", ""));
    }
}
