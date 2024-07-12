package com.kaiwa.userservice.repository;

import com.kaiwa.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameAndIsActiveIsTrue(String username);
    Optional<User> findByEmailAndIsActiveIsTrue(String email);
    Optional<User> findByIdAndIsActiveTrue(String id);
    List<User> findAllByIsActiveIsTrue();
    Boolean existsByIdAndIsActiveTrue(String id);
}
