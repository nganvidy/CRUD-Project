package com.example.demo.repository.provider;


import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

public class TransactionProvider {


    public static String getAllTransaction(){
    return new  SQL(){{
        SELECT("*");
        FROM("transaction_tb");
         }}.toString();
    }
    public static String insertTransaction(){
        return new SQL(){{
            INSERT_INTO("transaction_tb");
            VALUES("sender_account_id", "#{trans.senderAccountId}");
            VALUES("receive_account_id", "#{trans.receiveAccountId}");
            VALUES("amount", "#{trans.amount}");
            VALUES("remark", "#{trans.remark}");
            VALUES("transfer_at", "#{trans.transferAt}");
        }}.toString();

    }
    public static String updateTransaction(){
        return new SQL(){{
            UPDATE("transaction_tb");
            SET("sender_account_id=#{tran.senderAccountId}");
            SET("receive_account_id=#{tran.receiveAccountId}");
            SET("amount=#{tran.amount}");
            SET("remark=#{tran.remark}");
            SET("transfer_at=#{tran.transferAt}");
            WHERE("id=#{id}");
        }}.toString();
    }
    public static String deleteTransaction(){
        return new SQL(){{
            DELETE_FROM("transaction_tb");
            WHERE("id=#{id}");
        }}.toString();
    }

    public static String getAllTransactionAccount(){
        return new SQL(){{
            SELECT("*");
            FROM("transaction_tb");
        }}.toString();
    }
}
