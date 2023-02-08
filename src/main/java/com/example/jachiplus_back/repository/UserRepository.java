package com.example.jachiplus_back.repository;

import com.example.jachiplus_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
