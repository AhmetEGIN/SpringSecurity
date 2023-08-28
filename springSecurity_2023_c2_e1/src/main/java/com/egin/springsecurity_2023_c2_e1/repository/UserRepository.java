package com.egin.springsecurity_2023_c2_e1.repository;

import com.egin.springsecurity_2023_c2_e1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
    SELECT u FROM User u WHERE u.username = :username
    """)
    Optional<User> findUserByUsername(String username);

}
