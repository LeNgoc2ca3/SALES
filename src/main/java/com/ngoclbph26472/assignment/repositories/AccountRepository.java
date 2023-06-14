package com.ngoclbph26472.assignment.repositories;

import com.ngoclbph26472.assignment.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByUsername(String username);

    boolean existsAccountsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "update Accounts a set a.password = :newPassword where a.email = :email", nativeQuery = true)
    void updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);
}
