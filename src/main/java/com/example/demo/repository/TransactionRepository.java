package com.example.demo.repository;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionData;
import com.example.demo.model.UserTransaction;
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

    @Results({
            @Result(column = "sender_account_id",property = "senderAccount",one = @One(select = "getUserByIdTransaction")),
            @Result(column = "receive_account_id",property = "receiveAccount",one = @One(select = "getUserByIdTransaction")),
            @Result(column = "transfer_at",property = "transferAt")
    })
    @SelectProvider(type = TransactionProvider.class,method = "getAllTransactionData")
    List<TransactionData> getAllTransactionDataUser();
    @Select("""
            SELECT account_id ,a.account_no ,ut.* FROM user_account_tb
            INNER JOIN users_tb ut on user_account_tb.user_id = ut.id
            INNER JOIN account_tb a on a.id = user_account_tb.account_id
            WHERE account_id=#{id}
            """)
    @Results({
            @Result(column = "id",property = "accountId"),
            @Result(column = "account_no",property = "accountNumber"),
            @Result(column = "id",property = "user.id"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "gender",property = "user.gender"),
            @Result(column = "address",property = "user.address")
    })
    UserTransaction getUserByIdTransaction(int id);
}
