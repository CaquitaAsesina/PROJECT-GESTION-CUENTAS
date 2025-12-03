package com.example.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cuenta.modules.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
