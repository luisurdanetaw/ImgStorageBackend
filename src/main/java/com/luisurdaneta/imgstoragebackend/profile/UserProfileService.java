package com.luisurdaneta.imgstoragebackend.profile;

import com.luisurdaneta.imgstoragebackend.bucket.BucketName;
import com.luisurdaneta.imgstoragebackend.datastore.UserRepository;
import com.luisurdaneta.imgstoragebackend.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.StreamSupport;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserRepository userRepository;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserRepository userRepository, FileStore fileStore){
        this.userRepository = userRepository;
        this.fileStore = fileStore;
    }

    Iterable<UserProfile> getUserProfiles(){
        return userRepository.findAll();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        //Check if file not empty
        isFileEmpty(file);
        //Check if file is an img
        isFileImg(file);
        //Check if the user exists in our database

        UserProfile user = doesUserExist(userProfileId);

        //Grab metadata from file if any

        Map<String, String> metadata = getMetadata(file);

        //Store img to s3
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), userProfileId);

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(filename);
        } catch (IOException e){
            throw new IllegalStateException(e);
        }
        //Update database with s3 img link


    }

    private Map<String, String> getMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile doesUserExist(UUID userProfileId) {

        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    private void isFileImg(MultipartFile file) {
        List<String> fileTypes = List
                .of(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType());

        boolean typeCheck = fileTypes.contains(file.getContentType());

        if (!typeCheck){
            throw new IllegalStateException("File must be an image");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file");
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = doesUserExist(userProfileId);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId());

        return user.getUserProfileImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    public void createUser(String username) {
        UserProfile user = new UserProfile(username);
        userRepository.save(user);
    }
}
