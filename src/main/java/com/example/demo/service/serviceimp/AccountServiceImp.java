package com.example.demo.service.serviceimp;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImp implements AccountService {
    //Inject repository

   private final AccountRepository accountRepository;
    @Autowired
    AccountServiceImp(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public int addNewAccount(Account account) {
        return 0;
    }

    @Override
    public int updateAccount(Account account) {
        return 0;
    }

    @Override
    public int deleteAccount(Integer id) {
        return 0;
    }
}
