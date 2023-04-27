package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserAccount;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<User> findUserByName(String name);
    int createUser(User user);
    int updateUser(User user,Integer id);
    User findUserById(int id);
    int removeuser(Integer id);


    List<UserAccount> getAllUserAccount();
}
