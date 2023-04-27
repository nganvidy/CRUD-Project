package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import com.example.demo.utils.Response;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {
//    @Result(column = "id",property = "userId")
    @Select("SELECT * FROM users_tb")
    List<User> allUser();

    List<User> findUserByName(String username);

    @Select("INSERT INTO users_tb(username, gender, address) VALUES (#{user.username},#{user.gender},#{user.address}) returning id")
    int createUser(@Param("user") User user);

    @Select("UPDATE users_tb SET username=#{user.username},gender=#{user.gender},address=#{user.address} WHERE id=#{id} returning id")
    int updateUser(@Param("user") User user,@Param("id") Integer id);

    @Select("SELECT * FROM users_tb WHERE id=#{id}")
    User findUserById(int id);
    @Delete("DELETE FROM users_tb WHERE id=#{id}")
    int removeUser(@Param("id") Integer id);


    @Results({
            @Result(column = "id",property = "userId"),
            @Result(column = "id",property = "accounts",many = @Many(select = "findAccountUserById"))
    })
    @Select("SELECT * FROM users_tb")
    List<UserAccount> getAllUserAccount();


    @Results({
            @Result(property = "accountNumber",column = "account_no"),
            @Result(property = "accountName",column = "account_name"),
            @Result(property = "accountType",column = "account_type",one = @One(select = "com.example.demo.repository.AccountRepository.getAccountTypeById"))

    })
    @Select("""
            SELECT * FROM user_account_tb inner join account_tb a on a.id = user_account_tb.account_id
            WHERE user_id=#{id}
            """)
    List<Account> findAccountUserById(int id);
}
