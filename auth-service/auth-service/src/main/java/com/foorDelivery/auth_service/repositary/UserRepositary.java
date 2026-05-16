package com.foorDelivery.auth_service.repositary;

import com.foorDelivery.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositary extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
