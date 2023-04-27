package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.AccountType;
import com.example.demo.model.UserAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountRepository {
    @Results({
            @Result(column = "account_no",property = "accountNumber"),
            @Result(column = "account_name",property = "accountName"),
            @Result(column = "transfer_limit",property = "transferLimit"),
            @Result(column = "account_type",property = "accountType",one = @One(select = "getAccountTypeById"))
    })
    @Select("SELECT * FROM account_tb")
    List<Account> getAllAccount();
    @Result(column = "name",property = "accountName")
    @Select("SELECT * FROM accounttype_tb WHERE id=#{account_type}")
    AccountType getAccountTypeById(int account_type);
    int createAccount(Account account);
    int updateAccount(Account account);
    int deleteAccount(Integer id);
    Account findById(Integer id);


}
