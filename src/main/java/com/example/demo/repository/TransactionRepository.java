package com.example.demo.repository;

import com.example.demo.model.Transaction;
import com.example.demo.model.request.TransactionRequest;
import com.example.demo.model.respone.ResponseTransaction;
import com.example.demo.repository.provider.TransactionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface TransactionRepository {
    @Results({
            @Result(column = "sender_account_id",property = "senderAccountId"),
            @Result(column = "receive_account_id",property = "receiveAccountId"),
            @Result(column = "transfer_at",property = "transferAt")
    })
    @SelectProvider(type = TransactionProvider.class,method = "getAllTransaction")
    List<Transaction> getAllTransaction();

    @InsertProvider(type = TransactionProvider.class,method = "insertTransaction")
    int createTransaction(@Param("trans") TransactionRequest transaction);
    @UpdateProvider(type = TransactionProvider.class,method = "updateTransaction")
    int updateTransaction(@Param("tran") TransactionRequest transactionRequest,@Param("id") int id);
    @DeleteProvider(type = TransactionProvider.class,method = "deleteTransaction")
    int deleteTransaction(@Param("id") int id);

    @Results({
            @Result(column = "sender_account_id",property = "senderAccountId",one = @One(select = "com.example.demo.repository.TransactionAccountRepository.getTransactionAccount")),
            @Result(column = "receive_account_id",property = "receiveAccountId",one = @One(select = "com.example.demo.repository.TransactionAccountRepository.getTransactionAccount")),
            @Result(column = "transfer_at",property = "transferAt")
    })
    @SelectProvider(type = TransactionProvider.class,method = "getAllTransactionAccount")
    List<ResponseTransaction> getAllTransactionAccount();
}
