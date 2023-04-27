package com.example.demo.service.serviceimp;

import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> allUsers() {
        return userRepository.allUser();
    }

    @Override
    public List<User> findUserByName(String name) {
        return null;
    }

    @Override
    public int createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public int updateUser(User user,Integer id) {
        return userRepository.updateUser(user,id);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public int removeuser(Integer id) {
        return userRepository.removeUser(id);
    }

    @Override
    public List<UserAccount> getAllUserAccount() {
        return userRepository.getAllUserAccount();
    }
}
