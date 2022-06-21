package com.example.agentservice.repository;

import com.example.agentservice.model.WayaPosUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WayaPosUsersRepository extends JpaRepository<WayaPosUsers, Long> {
    Optional<WayaPosUsers> findByEmail(String email);
}