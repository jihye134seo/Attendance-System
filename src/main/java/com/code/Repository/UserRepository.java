package com.code.Repository;

import com.code.Entity.user_tb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<user_tb, Integer> {
}
