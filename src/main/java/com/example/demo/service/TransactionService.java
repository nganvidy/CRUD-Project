package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionData;
import com.example.demo.model.UserTransaction;
import com.example.demo.model.request.TransactionRequest;
import com.example.demo.model.respone.ResponseTransaction;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TransactionService {

    PageInfo<Transaction> getAllTransaction(int page,int limit);
    int createTransaction(TransactionRequest transaction);
    int updateTransaction(TransactionRequest transactionRequest,int id);
     int deleteTransaction(int id);
    PageInfo<ResponseTransaction> getAllTransactionaccount(int page, int limit);



    List<TransactionData> getAllDataTransactionAccount();
}
