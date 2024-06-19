package com.programming.techie.repository;

import com.programming.techie.model.Auth;
import java.util.Optional;

public interface SearchEngineUserByUserAuthentication<T> {
    Optional<T> findUserByUserAuthentication(Auth userAuth);
}
