package com.luisurdaneta.imgstoragebackend.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.luisurdaneta.imgstoragebackend.bucket.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class StorageService {
    private final AmazonS3 s3;

    @Autowired
    public StorageService (AmazonS3 s3){
        this.s3 = s3;
    }

    public void save(String path, String fileName, File file){
        try{
            s3.putObject(
                    new PutObjectRequest(
                            String.valueOf(Bucket.BUCKET_NAME),
                            fileName,
                            file)
            );
        }
        catch (AmazonServiceException e){
            throw new IllegalStateException("Upload unsuccessful", e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            return IOUtils.toByteArray(s3.getObject(path, key).getObjectContent());

        } catch (Exception e){
            throw new AmazonServiceException("Download failed: ", e);
        }
    }
}
