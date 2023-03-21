package com.code.JwtToken;

import com.code.Entity.user_tb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<user_tb, Long> {

    @Query(value = "SELECT * FROM attender.user_tb u where u.email_address = :email_address", nativeQuery = true)
    Optional<user_tb> findByEmail_address(String email_address);





}
