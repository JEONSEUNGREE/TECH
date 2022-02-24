package com.example.FreemarkerShop.repository;

import com.example.FreemarkerShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}