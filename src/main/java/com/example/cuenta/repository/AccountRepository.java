package com.example.cuenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cuenta.modules.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long user_id);

    List<Account> findByGmailId(Long gmail_id);

    List<Account> findByTipo(String tipo);

    List<Account> findByActivo(Boolean activo);

    List<Account> findByUserIdAndActivo(Long user_id, Boolean activo);

    List<Account> findByUserIdAndTipo(Long user_id, String tipo);

    List<Account> findByTipoAndActivo(String tipo, Boolean activo);

    Long countByUserId(Long id);

    Long countByUserIdAndActivo(Long user_id, Boolean activo);
}
