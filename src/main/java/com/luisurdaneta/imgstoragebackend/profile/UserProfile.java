package com.luisurdaneta.imgstoragebackend.profile;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Entity
public class UserProfile {

    //userProfileImageLink not final because database is in memory
    @Id
    private Long id;
    private UUID userProfileId;
    private String username;
    private String userProfileImageLink; //S3 Key


    public UserProfile(){
        this.id = generateId();
        this.userProfileId = UUID.randomUUID();
        this.username = null;
        this.userProfileImageLink = null;
    }

    public UserProfile(String username){
        this.id = generateId();
        this.userProfileId = UUID.randomUUID();
        this.username = username;
        this.userProfileImageLink = null;
    }

    public UserProfile(UUID userProfileId, String username, String userProfileImageLink) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.userProfileImageLink = userProfileImageLink;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    public String getUsername() {
        return username;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImageLink);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private Long generateId() {
        Random rn = new Random();
        return rn.nextLong();
    }

}

