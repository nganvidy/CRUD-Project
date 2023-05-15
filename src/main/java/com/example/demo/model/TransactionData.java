package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
    private  int id;
    private UserTransaction senderAccount;
    private UserTransaction receiveAccount;
    private float amount;
    private String remark;
    private Date transferAt;
}
