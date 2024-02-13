package com.example.bankingapplication.Controller;

import com.example.bankingapplication.Dto.LoginResponseDto;
import com.example.bankingapplication.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String registerUser(@RequestParam("username")String username,@RequestParam("password")String password){
        return authenticationService.registerUser(username,password);
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestParam("username")String username, @RequestParam("password")String password){
        return authenticationService.loginUser(username, password);
    }
}