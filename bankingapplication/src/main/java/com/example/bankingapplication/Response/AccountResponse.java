package com.example.bankingapplication.Response;

import lombok.Data;

import java.util.Date;

@Data
public class AccountResponse {
    private String fullName;
    private String gender;
    private String phone;
    private double balanceBefore;
    private double balanceAfter;
    private double withdrawAmount;
    private int accountNumber;
    private Date datecreated;

}
