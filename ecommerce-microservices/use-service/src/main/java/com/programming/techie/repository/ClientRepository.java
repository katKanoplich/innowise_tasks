package com.programming.techie.repository;


import com.programming.techie.model.Auth;
import com.programming.techie.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, SearchEngineUserByUserAuthentication<Client> {
    Optional<Client> findByAuthentication(Auth auth);
    default Optional<Client> findUserByUserAuthentication(Auth userAuth) {
        return findByAuthentication(userAuth);
    }

}
