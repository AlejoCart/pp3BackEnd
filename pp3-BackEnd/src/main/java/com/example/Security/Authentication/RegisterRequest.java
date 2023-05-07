package com.example.Security.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String surname;
    private String username;
    private String password;

    private Date birthdate;

    private String career;

    private String img;
    //private String email;


}
