package com.example.bankingapplication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ussdaccount")
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Basic
    @Column(name = "gender", nullable = false)
    private String gender;

    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;


    @Basic
    @Column(name = "balance_before" , nullable = false)
    private double balanceBefore;


    @Basic
    @Column(name = "balance_after" , nullable = false)
    private double balanceAfter;


    @Basic
    @Column(name = "withdraw_amount" , nullable = false)
    private double withdrawAmount;


    @Basic
    @Column(name = "account_number" , nullable = false)
    private double accountNumber;


    @Basic
    @Column(name = "date_created" , nullable = false)
    private Date dateCreated;


}
