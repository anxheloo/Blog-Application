package com.blogExample.springbootblogapplication.Services;

import com.blogExample.springbootblogapplication.Repository.AccountRepository;
import com.blogExample.springbootblogapplication.models.Account;
import com.blogExample.springbootblogapplication.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public Account save(Account account)
    {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }


    public Optional<Account> findByEmail(String email)
    {
        return accountRepository.findByEmail(email);
    }

}
