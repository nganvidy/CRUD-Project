package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import com.example.demo.model.request.UserRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<User> findUserByName(String username);
    int createUser(UserRequest user);
    int updateUser(UserRequest user,Integer id);
    User findUserById(int id);
    int removeuser(Integer id);


    List<UserAccount> getAllUserAccount();


    List<User> findAllUserBySorted(String field);


    List<User> paginationPageBySQLCode(int limit,int page);
}
