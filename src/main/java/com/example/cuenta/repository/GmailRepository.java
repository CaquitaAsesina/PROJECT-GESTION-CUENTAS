package com.example.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cuenta.modules.Gmail;

@Repository
public interface GmailRepository extends JpaRepository<Gmail, Long> {

}
