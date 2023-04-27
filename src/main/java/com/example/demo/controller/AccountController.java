package com.example.demo.controller;

import com.example.demo.mapper.AccountMapper;
import com.example.demo.model.Account;
import com.example.demo.model.respone.AccountResponse;
import com.example.demo.service.AccountService;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountMapper accountMapper;
@GetMapping("/alluser")
    public Response<List<AccountResponse>> getAllAccount(){
        try {
            List<Account> allAccount = accountService.getAllAccount();
            List<AccountResponse> accountResponses = accountMapper.mapToAccountResponse(allAccount);
            return Response.<List<AccountResponse>>ok().setPayload(accountResponses).setMessage("Successfully retrieved all account information.");
//            Map<String,Object> response=new HashMap<>();
//            response.put("payload",accountResponses);
//            response.put("message","Successfully retrieve all account info.");
//            response.put("status", HttpStatus.OK);
//            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            System.out.println("Something wrong"+e.getMessage());
            return Response.<List<AccountResponse>>exception().setMessage("Exception occurs! failed to retrieved account information");
//            return ResponseEntity.ok().body("failed to retreived the account");
        }
    }
}
