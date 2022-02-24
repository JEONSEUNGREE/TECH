package com.example.freemarker.repository;

import com.example.freemarker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    User findByName(String name);

    @Query("select u from User u join fetch Role r where u.email = :email")
    User getUserInfo(@Param("email") String email);
}
