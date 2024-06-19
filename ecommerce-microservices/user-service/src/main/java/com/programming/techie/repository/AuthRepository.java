package com.programming.techie.repository;

import com.programming.techie.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {
    Auth findByLogin(String login);
    Auth findByPassword(String password);
}
