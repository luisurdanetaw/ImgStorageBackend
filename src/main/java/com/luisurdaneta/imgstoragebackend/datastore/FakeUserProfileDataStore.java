package com.luisurdaneta.imgstoragebackend.datastore;

import com.luisurdaneta.imgstoragebackend.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static{
        USER_PROFILES.add(new UserProfile(UUID.fromString("d527178c-fe32-4694-8938-4a6cdd111db7"),"user123", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("b74bf63b-b127-44df-b7ee-6768b3ea1c04"),"user2021", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
