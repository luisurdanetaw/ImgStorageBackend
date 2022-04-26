package com.luisurdaneta.imgstoragebackend.datastore;

import com.luisurdaneta.imgstoragebackend.profile.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserProfile, Long> {

}