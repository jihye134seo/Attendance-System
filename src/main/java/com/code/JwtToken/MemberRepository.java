package com.code.JwtToken;

import com.code.Entity.user_tb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<user_tb, Long> {
    Optional<user_tb> findByEmailAddress(String email_address);
}
