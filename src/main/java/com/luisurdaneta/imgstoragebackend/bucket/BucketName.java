package com.luisurdaneta.imgstoragebackend.bucket;

public enum BucketName {

    PROFILE_IMAGE("img-upload-bucket-luisurdaneta");

    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }
}
