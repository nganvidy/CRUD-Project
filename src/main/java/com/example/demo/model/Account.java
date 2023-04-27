package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String accountNumber;
    private String accountName;
//    private int user_id;
    private String profile;
    private int pin;
    private String passcode;
    private String phoneNumber;
    private int transferLimit;
//    private int accountType;
    private AccountType accountType;
}
