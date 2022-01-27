package com.example.demo.repository;

import com.example.demo.model.User;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// JpaRepository allows us to perform various operations as it extends CrudRepository
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    // after extending this repository we are able to leverage crud operations on our user table
}
