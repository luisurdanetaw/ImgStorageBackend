package com.luisurdaneta.imgstoragebackend.profile;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
public class Employee {

    @Id
    @SequenceGenerator(
            name = "sequence",
            sequenceName = "sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private UUID userProfileId;
    private String username;
    private String userProfileImageLink;
    private float salary;


    public Employee(){
        this.userProfileId = UUID.randomUUID();
        this.username = null;
        this.userProfileImageLink = null;
        this.salary = 0F;
    }

    public Employee(String username){
        this.userProfileId = UUID.randomUUID();
        this.username = username;
        this.userProfileImageLink = null;
        this.salary = 0F;
    }

    public Employee(UUID userProfileId, String username, String userProfileImageLink) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.userProfileImageLink = userProfileImageLink;
        this.salary = 0F;

    }
    public Employee(UUID userProfileId, String username, String userProfileImageLink, float salary) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.userProfileImageLink = userProfileImageLink;
        this.salary = salary;

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
        Employee that = (Employee) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImageLink);
    }


    public float getSalary() {
        return salary;
    }
    public void setSalary (float salary){
        this.salary = salary;
    }
}

