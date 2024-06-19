package com.programming.techie.repository;

import com.programming.techie.model.Admin;
import com.programming.techie.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, SearchEngineUserByUserAuthentication<Admin> {

    Optional<Admin> findByAuthentication(Auth auth);
    default Optional<Admin> findUserByUserAuthentication(Auth userAuth) {
        return findByAuthentication(userAuth);
    }
}
