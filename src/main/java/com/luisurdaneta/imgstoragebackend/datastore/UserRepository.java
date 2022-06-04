package com.luisurdaneta.imgstoragebackend.datastore;

import com.luisurdaneta.imgstoragebackend.profile.Employee;
import com.luisurdaneta.imgstoragebackend.profile.WageDifferenceGraph;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Employee, Long> {
    WageDifferenceGraph graph = new WageDifferenceGraph();
}