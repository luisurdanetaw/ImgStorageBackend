package com.luisurdaneta.imgstoragebackend.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("employees/v1/")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService userProfileService;

    @Autowired
    public EmployeeController(EmployeeService userProfileService){
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public Iterable<Employee> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "/{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file") File file) throws IOException {

        userProfileService.upload(userProfileId, file);
    }
    @GetMapping("/{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId){
        return userProfileService.download(userProfileId);
    }

    @RequestMapping(path = "/{username}/create-user")
    public void createUser(@PathVariable("username") String username){
        userProfileService.createUser(username);
    }

}
