package com.luisurdaneta.imgstoragebackend.profile;

import com.amazonaws.AmazonServiceException;
import com.luisurdaneta.imgstoragebackend.bucket.Bucket;
import com.luisurdaneta.imgstoragebackend.datastore.UserRepository;
import com.luisurdaneta.imgstoragebackend.filestore.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class EmployeeService {

    private final UserRepository userRepository;
    private final StorageService storageService;

    @Autowired
    public EmployeeService(UserRepository userRepository, StorageService storageService){
        this.userRepository = userRepository;
        this.storageService = storageService;
    }

    Iterable<Employee> getUserProfiles(){
        return userRepository.findAll();
    }

    public void upload(UUID userProfileId, File file) throws IOException {
        if(file.length() == 0) throw new IllegalStateException("Cannot upload empty file");
        isImg(file);

        Employee user = isUserValid(userProfileId);

        String path = String.format("%s/%s", Bucket.BUCKET_NAME.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getName(), userProfileId);

        storageService.save(path, filename, file);
        user.setUserProfileImageLink(filename);

    }

    private Employee isUserValid(UUID userProfileId) {
        Iterable<Employee> list = userRepository.findAll();

        for(Employee user : list){
            if(user.getUserProfileId().equals(userProfileId))
                return user;
        }
        throw new IllegalStateException("User not valid");
    }

    private void isImg(File file) throws IOException {
        List<String> formats = List
                .of(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType());

        if(!formats
                .contains(
                    Files.probeContentType(
                            Path.of(file.getPath())
                    )
                )
        )
        throw new IllegalStateException("File must be an image");
    }


    public byte[] download(UUID userProfileId) {
        Employee user = isUserValid(userProfileId);

        //doesUserExist(userProfileId);
        String path = String.format("%s/%s",
                Bucket.BUCKET_NAME.getBucketName(),
                user.getUserProfileId());

        try{
            return storageService.download(path, user.getUserProfileImageLink().toString());
        }
        catch(AmazonServiceException e){
            return new byte[0];
        }
    }

    public void createUser(String username) {
        Employee user = new Employee(username);
        userRepository.save(user);

    }
}
