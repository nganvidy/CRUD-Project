package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionData;
import com.example.demo.model.request.TransactionRequest;
import com.example.demo.model.respone.ResponseTransaction;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.Response;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    Response<PageInfo<Transaction>> getAllTransaction(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int limit){
        try {
            PageInfo<Transaction> transactionPageInfo=transactionService.getAllTransaction(page, limit);
           return Response.<PageInfo<Transaction>>ok().setPayload(transactionPageInfo).setMessage("Here is your data.");
        }catch (Exception ex){
            System.out.println("Error :"+ex);
            return Response.<PageInfo<Transaction>>exception().setMessage("Error Exception "+ex).setSuccess(false);
        }

    }
    @PostMapping("/")
    Response<Transaction> createTransaction(@RequestBody @Valid TransactionRequest transaction){
        try {
            System.out.println(transaction);
            int results= transactionService.createTransaction(transaction);
            if(results>0){
                Transaction newTransaction=new Transaction().setSenderAccountId(transaction.getSenderAccountId())
                        .setReceiveAccountId(transaction.getReceiveAccountId())
                        .setAmount(transaction.getAmount())
                        .setRemark(transaction.getRemark())
                        .setTransferAt(transaction.getTransferAt());
                return Response.<Transaction>createSuccess().setPayload(newTransaction).setMessage("You're create transaction successfully");
            }else {
                return Response.<Transaction>badRequest().setMessage("Your insert is error").setSuccess(false);
            }

        }catch (Exception ex){
            System.out.println("error : "+ex);
            return Response.<Transaction>exception().setMessage("Error Exception").setSuccess(false);
        }

    }
    @PutMapping("/{id}")
    Response<Transaction> updateTransaction(@RequestBody TransactionRequest transactionRequest,@PathVariable("id") int id){
        try {
            Date date=new Date();
            transactionRequest.setTransferAt(date);
            System.out.println("This is value: "+transactionRequest);
            int results= transactionService.updateTransaction(transactionRequest,id);
            System.out.println("this is result :"+results);
            if(results>0){
                Transaction transaction= new Transaction().setSenderAccountId(transactionRequest.getSenderAccountId())
                        .setReceiveAccountId(transactionRequest.getReceiveAccountId())
                        .setAmount(transactionRequest.getAmount())
                        .setRemark(transactionRequest.getRemark())
                        .setTransferAt(date);
                return Response.<Transaction>updateSuccess().setPayload(transaction).setMessage("You're Update Successfully");
            }else {
                return Response.<Transaction>badRequest().setMessage("Your are failed update data.").setSuccess(false);
            }
        }catch (Exception ex){
            System.out.println("error : "+ex);
            return Response.<Transaction>exception().setMessage("Error Exception").setSuccess(false);
        }
    }
    @DeleteMapping("/{id}")
    Response<Transaction> deleteTransaction(@PathVariable("id") int id){
        try {
            int results= transactionService.deleteTransaction(id);
            if(results>0){
                return Response.<Transaction>deleteSuccess().setSuccess(true).setMessage("You're delete successfully");
            }else {
                return Response.<Transaction>badRequest().setMessage("Your are failed delete data.").setSuccess(false);
            }
        }catch (Exception ex){
            System.out.println("error : "+ex);
            return Response.<Transaction>exception().setMessage("Error Exception").setSuccess(false);
        }
    }

    @GetMapping("/all")
    Response<PageInfo<ResponseTransaction>> getAllTransactionAccount(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int limit){
        try {
            PageInfo<ResponseTransaction> transactionPageInfo=transactionService.getAllTransactionaccount(page, limit);
            return Response.<PageInfo<ResponseTransaction>>ok().setPayload(transactionPageInfo).setMessage("Here is your data.");
        }catch (Exception ex){
            System.out.println("Error :"+ex);
            return Response.<PageInfo<ResponseTransaction>>exception().setMessage("Error Exception "+ex).setSuccess(false);
        }
    }

    @GetMapping("/allData")
    Response<List<TransactionData>> getAllDataTransactionUser(){
       try {
           List<TransactionData> transactionData=transactionService.getAllDataTransactionAccount();
           return Response.<List<TransactionData>>ok().setPayload(transactionData).setSuccess(true).setMessage("This is your data.");
       }catch (Exception ex){
           System.out.println("This is error :"+ex);
           return Response.<List<TransactionData>>exception().setMessage("Error exception "+ex);
       }
    }

}
