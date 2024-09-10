package com.example.user_service.repository;

import com.example.user_service.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);

    Optional<UserEntity> findByEmail(String email);
}
