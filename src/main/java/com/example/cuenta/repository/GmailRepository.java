package com.example.cuenta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cuenta.modules.Gmail;

@Repository
public interface GmailRepository extends JpaRepository<Gmail, Long> {

    Optional<Gmail> findByCorreo(String correo);

    List<Gmail> findByUserId(Long user_id);

    List<Gmail> findByEstado(String estado);

    boolean existsByCorreo(String correo);

}
