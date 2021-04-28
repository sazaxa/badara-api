package com.sazaxa.shipmentapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AwsConfig {
    @Value("${aws.access_key_id}")
    private String accessKeyId;

    @Value("${aws.secret_access_key}")
    private String SecretAccessKey;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getSecretAccessKey() {
        return SecretAccessKey;
    }

    public String getBucketName() {
        return bucketName;
    }
}
