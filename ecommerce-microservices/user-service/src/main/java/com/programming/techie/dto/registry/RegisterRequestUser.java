package com.programming.techie.dto.registry;

import com.programming.techie.model.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestUser {
    private String firstName;
    private String lastName;
    private String patronymic;
    private int age;
    private String passportId;
    private int passportNumber;
    private Auth auth;
}
