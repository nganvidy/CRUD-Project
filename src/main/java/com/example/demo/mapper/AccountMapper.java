package com.example.demo.mapper;

import com.example.demo.model.Account;
import com.example.demo.model.respone.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    //Account to Account Response
    List<AccountResponse> mapToAccountResponse(List<Account> accounts);
    //Response to Account
    List<Account> mapToAccount(List<AccountResponse> accountResponses);
}
