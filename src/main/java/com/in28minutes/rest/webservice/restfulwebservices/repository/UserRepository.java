package com.in28minutes.rest.webservice.restfulwebservices.repository;

import com.in28minutes.rest.webservice.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
