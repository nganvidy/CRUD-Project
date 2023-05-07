package com.example.demo.service.serviceimp;

import com.example.demo.model.Transaction;
import com.example.demo.model.request.TransactionRequest;
import com.example.demo.model.respone.ResponseTransaction;
import com.example.demo.repository.TransactionAccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImp implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionAccountRepository transactionAccountRepository;
    @Override
    public PageInfo<Transaction> getAllTransaction(int page,int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>(transactionRepository.getAllTransaction());
    }

    @Override
    public int createTransaction(TransactionRequest transaction) {
        return transactionRepository.createTransaction(transaction);
    }

    @Override
    public int updateTransaction(TransactionRequest transactionRequest, int id) {
        return transactionRepository.updateTransaction(transactionRequest,id);
    }

    @Override
    public int deleteTransaction(int id) {
        return transactionRepository.deleteTransaction(id);
    }

    @Override
    public PageInfo<ResponseTransaction> getAllTransactionaccount(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>(transactionRepository.getAllTransactionAccount());
    }

}
