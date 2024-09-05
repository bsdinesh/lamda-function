package com.expanse;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.util.IOUtils;
import com.expanse.entity.TenantConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class S3ToDynamoDBLambda implements RequestHandler<S3Event, String> {

    private AmazonS3 s3client;
    private DynamoDBMapper dynamoDBMapper;


    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        String bucketName = s3Event.getRecords().get(0).getS3().getBucket().getName();
        String fileName = s3Event.getRecords().get(0).getS3().getObject().getKey();
        try {
            initS3Client();
            InputStream inputStream = s3client.getObject(bucketName, fileName).getObjectContent();
            String content = IOUtils.toString(inputStream);
            context.getLogger().log("file content ::: " + content);
            //read content from s3 bucket & save to dynamoDB table
            TenantConfiguration tenantConfiguration = new ObjectMapper().readValue(content, TenantConfiguration.class);
            initDynamoDB();
            dynamoDBMapper.save(tenantConfiguration);
            context.getLogger().log("successfully save data to dynamoDB");

        } catch (IOException e) {
            return "Error while reading file from S3 :::" + e.getMessage();
        }

        return "successfully save data to dynamoDB";
    }

    private void initS3Client() {
        s3client = AmazonS3ClientBuilder
                .standard()
                .build();
    }

    private void initDynamoDB(){
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        dynamoDBMapper = new DynamoDBMapper(client);
    }
}
