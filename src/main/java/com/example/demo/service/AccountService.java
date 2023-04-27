package com.example.demo.service;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccount();
    Account findById(Integer id);
    int addNewAccount(Account account);
    int updateAccount(Account account);
    int deleteAccount(Integer id);
}
