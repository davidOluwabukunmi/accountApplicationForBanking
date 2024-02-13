package com.example.bankingapplication.Controller;

import com.example.bankingapplication.Dto.AccountDto;
import com.example.bankingapplication.Response.AccountResponse;
import com.example.bankingapplication.Service.AccountService;
import com.example.bankingapplication.Utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankingapplication/")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;


    @PostMapping("addAccount")
    public AccountResponse addAccount (@RequestBody AccountDto request){
       return accountService.addAccount(request);

    }

    @PutMapping("deposit")
    public AccountResponse deposit (@RequestParam("phone") String phone, @RequestParam("amount") double amount){
        return accountService.deposit(phone, amount);
    }

    @PutMapping("redeposit")
    public AccountResponse redeposit (@RequestParam("phone")String phone, @RequestParam("amount") double amount){
        return accountService.redeposit(phone, amount);
    }

    @PostMapping("withdraw")
    public AccountResponse withdraw (@RequestParam("phone") String phone, @RequestParam("amount") double amount){
        return accountService.withdraw(phone, amount);
    }


    @GetMapping("checkBalance")
    public double checkBalance (@RequestParam("phone") String phone){
        return accountService.checkBalance(phone);
    }


    @DeleteMapping("deleteAccount")
    public String deleteAccount (@RequestParam("Id") Long id){
        return accountService.deleteAccount(id);
    }

    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }
}
