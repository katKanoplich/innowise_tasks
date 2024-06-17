package com.programming.techie.service;

import com.programming.techie.model.Auth;
import com.programming.techie.repository.AuthRepository;
import com.programming.techie.repository.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    public List<Auth> listAuthentication(){
        return authRepository.findAll();
    }
    public void addAuthentication(Auth auth){
        auth.setPassword(auth.getPassword());
        authRepository.save(auth);
    }
    public String loginAuthentication(Auth auth){
        if(auth.getLogin() == null || authRepository.findByLogin(auth.getLogin()) == null){
            return "Неверный логин";
        }
        if(auth.getPassword() == null || authRepository.findByPassword(auth.getPassword()) == null){
            return "Неверный логин";
        }
        return "Вход";
    }
    public void deleteAuthentication(Integer id){
        authRepository.deleteById(id);
    }
    public void updateAuthentication(Integer id, Auth upAuth){
        upAuth.setId(id);
        authRepository.save(upAuth);
    }
    public Auth getAuthenticationById(Integer id){
        return authRepository.findById(id).orElseThrow();
    }
    public Auth getAuthenticationByLogin(String login){
        return authRepository.findByLogin(login);
    }

}
