package com.example.jwttutorial.repository;

import com.example.jwttutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    eager조회로 가져오도록 설정 (MtM default = LAZY)
    @EntityGraph(attributePaths = "authorities")
//    queryMethod사용
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
