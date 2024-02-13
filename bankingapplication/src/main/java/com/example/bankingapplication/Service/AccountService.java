package com.example.bankingapplication.Service;

import com.example.bankingapplication.Dto.AccountDto;
import com.example.bankingapplication.Model.Accounts;
import com.example.bankingapplication.Repo.AccountRepo;
import com.example.bankingapplication.Response.AccountResponse;
import com.example.bankingapplication.Utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.plaf.OptionPaneUI;
import java.awt.*;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    public final AccountRepo accountRepo;
    public final ModelMapper modelMapper;

    public AccountResponse addAccount (AccountDto request){
        Accounts accounts = new Accounts();

        accounts.setFullName(request.getFirstName() + " " + request.getLastName());
        accounts.setGender(request.getGender());
        accounts.setPhone(request.getPhone());
        accounts.setBalanceBefore(0.00);
        accounts.setBalanceAfter(0.00);
        accounts.setWithdrawAmount(0.00);
        accounts.setAccountNumber(AccountUtils.generateAccountNumber());
        accounts.setDateCreated(new Date());
        accountRepo.save(accounts);
        return modelMapper.map(accounts, AccountResponse.class);
    }



    public AccountResponse deposit (String phone, double amount) {
        Optional<Accounts> accountsOptional = accountRepo.findByPhone(phone);
        if(accountsOptional.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }else{
            Accounts accounts = accountsOptional.get();
            accounts.setBalanceAfter(accounts.getBalanceBefore() + amount);
            accountRepo.save(accounts);
            return modelMapper.map(accounts, AccountResponse.class);
        }

    }


    public AccountResponse redeposit (String phone, double amount){
        Optional<Accounts> accountsOptional = accountRepo.findByPhone(phone);
        if(accountsOptional.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }else {
            Accounts accounts = accountsOptional.get();
            accounts.setBalanceBefore(accounts.getBalanceAfter());
            accounts.setBalanceAfter(accounts.getBalanceAfter() + amount);
            accountRepo.save(accounts);
            return modelMapper.map(accounts, AccountResponse.class);
        }
    }


//    public AccountResponse withdraw (String phone, double amount){
//        Optional<Accounts> accountsOptional = accountRepo.findByPhone(phone);
//        if(accountsOptional.isEmpty()){
//            throw new RuntimeException("Account Not Found");
//        }else {
//            Accounts accounts = accountsOptional.get();
//            accounts.setWithdrawAmount(amount);
//            accounts.setBalanceAfter(accounts.getBalanceAfter() - amount);
//            accountRepo.save(accounts);
//            return modelMapper.map(accounts, AccountResponse.class);
//        }
//    }


    public AccountResponse withdraw(String phone, double amount) {
        Optional<Accounts> accountsOptional = accountRepo.findByPhone(phone);

        if (accountsOptional.isEmpty()) {
            throw new RuntimeException("Account not found for phone: " + phone);
        }

        Accounts account = accountsOptional.get();

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }

        if (amount > account.getBalanceAfter()) {
            throw new RuntimeException("Insufficient balance for withdrawal");
        }

        account.setWithdrawAmount(amount);
        account.setBalanceAfter(account.getBalanceAfter() - amount);
        accountRepo.save(account);
        return modelMapper.map(account, AccountResponse.class);
    }


    public double checkBalance (String phone ){
        Optional<Accounts> accountsOptional = accountRepo.findByPhone(phone);
        if(accountsOptional.isEmpty()){
            throw new RuntimeException("Account Not Found");
        }else{
            Accounts accounts = accountsOptional.get();
            double balance = accounts.getBalanceAfter();
            return balance;
        }
    }


    public String deleteAccount (Long Id){
        Optional<Accounts> accountsOptional = accountRepo.findById(Id);
        if(accountsOptional.isEmpty()){
            throw new RuntimeException("User Not Found");
        }else{
            Accounts accounts = new Accounts();
            accountRepo.delete(accounts);
            return "Account Successfully Deleted";
        }

    }
}
