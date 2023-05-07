package com.example.demo.service.serviceimp;

import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import com.example.demo.model.request.UserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public PageInfo<User> allUsers(int page,int size,String filterName) {
        //pageHelper is here
        PageHelper.startPage(page,size);
         return new PageInfo<>(userRepository.allUser(filterName));
//        return userRepository.allUser();
    }

    @Override
    public List<User> findUserByName(String username) {
       return userRepository.findUserByName(username);
    }

    @Override
    public int createUser(UserRequest user) {
        return userRepository.createUser(user);
    }

    @Override
    public int updateUser(UserRequest user,Integer id) {
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

    @Override
    public List<User> findAllUserBySorted(String field) {
        return userRepository.findAllBySorted(field);
    }

    @Override
    public List<User> paginationPageBySQLCode(int limit, int page) {
        return userRepository.paginationFiendAll(limit,page);
    }
}
