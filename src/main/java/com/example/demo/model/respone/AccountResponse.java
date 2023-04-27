package com.example.demo.model.respone;

import com.example.demo.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
        private int id;
        private String accountNumber;
        private String accountName;
        private String profile;
        private String phoneNumber;
        private int transferLimit;
//        private  int accountType;
        private AccountType accountType;

}
