package com.example.demo.model.respone;

import com.example.demo.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransaction {
    private  int id;
    private Account senderAccountId;
    private Account receiveAccountId;
    private float amount;
    private String remark;
    private Date transferAt;
}
