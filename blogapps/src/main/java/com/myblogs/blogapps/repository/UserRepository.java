package com.myblogs.blogapps.repository;

import com.myblogs.blogapps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username,String email);
    // when ever we are using findby we are finding,reterving,and returning an object
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    // when we use exists it only checks if the data exists or not if yes it gives true
    // which means data cannot be created
}
