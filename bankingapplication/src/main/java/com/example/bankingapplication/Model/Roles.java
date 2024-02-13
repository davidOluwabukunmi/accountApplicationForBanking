package com.example.bankingapplication.Model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name="roles")
public class Roles implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer roleId;
    @Column(name="authority")
    private String authority;

    @Column(name="user_id")
    private Integer userId;

    public Roles(){
        super();
    }

    public Roles(String authority){
        this.authority = authority;
    }

    public Roles(Integer roleId, String authority){
        this.roleId = roleId;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.authority;
    }

    public void setAuthority(String authority){
        this.authority = authority;
    }



}
