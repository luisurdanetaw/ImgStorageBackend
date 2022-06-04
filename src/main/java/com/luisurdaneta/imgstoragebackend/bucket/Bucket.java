package com.luisurdaneta.imgstoragebackend.bucket;

public enum Bucket {

    BUCKET_NAME("img-upload-bucket-luisurdaneta");

    private final String bucketName;

    Bucket(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }
}
