package com.example.bankingapplication.Repo;

import com.example.bankingapplication.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByPhone(String phone);
}