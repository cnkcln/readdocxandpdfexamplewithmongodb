package com.osi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.osi.domain.EmpDetails;

public interface EmployeeDetailsRepository extends MongoRepository<EmpDetails, String> {

  EmpDetails save(EmpDetails empDetails);

}
