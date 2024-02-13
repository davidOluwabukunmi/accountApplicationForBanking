package com.example.bankingapplication.Dto;


import com.example.bankingapplication.Model.Users;

public class LoginResponseDto {
    private Users users;
    private String jwt;

    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(Users user, String jwt){
        this.users = user;
        this.jwt = jwt;
    }

    public Users getUser(){
        return this.users;
    }

    public void setUser(Users user){
        this.users = user;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}
