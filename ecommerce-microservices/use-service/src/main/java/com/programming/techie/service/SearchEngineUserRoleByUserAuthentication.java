package com.programming.techie.service;


import com.programming.techie.exceptions.UserAuthenticationException;
import com.programming.techie.model.Auth;
import com.programming.techie.repository.SearchEngineUserByUserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchEngineUserRoleByUserAuthentication {

    private final Map<String, SearchEngineUserByUserAuthentication> map;

    public UserDetails searchUser(Auth auth) {
        for (SearchEngineUserByUserAuthentication searchEngine : map.values()) {
            Optional<? extends UserDetails> userDetailsOptional = searchEngine.findUserByUserAuthentication(auth);
            if (userDetailsOptional.isPresent()) {
                return userDetailsOptional.get();
            }
        }
        throw new UserAuthenticationException("Пользователь не найден SearchEngineUserByUserAuthentication");
    }

}
