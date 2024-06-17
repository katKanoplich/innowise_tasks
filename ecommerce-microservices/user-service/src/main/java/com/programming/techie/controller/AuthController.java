package com.programming.techie.controller;

import com.programming.techie.model.Auth;
import com.programming.techie.repository.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;
    @GetMapping
    public List<Auth> getAllAuth(@RequestParam(name = "login", required = false) String login){
        if (login != null) {
            Auth auth = authService.getAuthenticationByLogin(login);
            return (auth == null) ? null : List.of(auth);
        }
        return authService.listAuthentication();
    }
    @PostMapping("/add")
    public ResponseEntity<String> addAuth(@RequestBody Auth auth){
        authService.addAuthentication(auth);
        return ResponseEntity.ok("Успешно добалено");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delAuth(@PathVariable Integer id){
        authService.deleteAuthentication(id);
        return ResponseEntity.ok("Успешно удалено");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuth(@PathVariable Integer id, @RequestBody Auth auth){
        authService.updateAuthentication(id, auth);
        return ResponseEntity.ok("Данные обновлены");
    }
    @GetMapping("/{id}")
    public Auth getAuth(@PathVariable Integer id){
        return authService.getAuthenticationById(id);
    }
}