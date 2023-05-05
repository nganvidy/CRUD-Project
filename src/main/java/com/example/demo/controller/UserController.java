package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import com.example.demo.model.request.UserRequest;
import com.example.demo.service.UserService;
import com.example.demo.utils.Response;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    Response<List<User>> getAllUser(){
        try {
           List<User> users= userService.allUsers();
            return Response.<List<User>>ok().setPayload(users).setMessage("This Is Your Data");
        }catch (Exception e){
            System.out.println(e);
            return Response.<List<User>>exception().setMessage("You Data is failed to get information").setSuccess(false);
        }
    }
    @GetMapping("/find/{id}")
    Response<User> findUserById(@PathVariable("id") int id){
        try {

            User user = userService.findUserById(id);
            System.out.println(user);
            if(user!=null) {
                return Response.<User>ok().setPayload(user).setMessage("You are found user by id :" + id);
            }else {
                return Response.<User>notFound().setMessage("Not Found User By Id :"+id);
            }
        }catch (Exception e){
            System.out.println(e);
              return Response.<User>ok().setMessage("Not Found User By Id :"+id).setSuccess(true);
        }
    }
    @PostMapping("/add")
    public Response<User> addUser(@Valid  @RequestBody UserRequest request){
        try {
            int effectedRow=userService.createUser(request);
            if(effectedRow>0){
                User response=new User().setUsername(request.getUsername())
                        .setGender(request.getGender())
                        .setAddress(request.getAddress())
                        .setId(effectedRow);
                return Response.<User>createSuccess().setPayload(response).setMessage("Effected Row Successfully.");
            }else {
                return Response.<User>badRequest().setMessage("Bad Request ! Failed to create user");
            }
        }catch (Exception ex){
            System.out.println("Hello This is your error :"+ex);
            return Response.<User>exception().setMessage("Exception occur failed to create a new user").setSuccess(false);
        }
    }
//    @PostMapping("/add")
//    public Response<User> addUser(@RequestBody User user){
//        try {
//            int result=userService.createUser(user);
//            System.out.println("here is the value of result : "+result);
//            if (result>0) {
//                user.setId(result);
//                return Response.<User>createSuccess().setPayload(user).setMessage("One Row Insert Successfully");
//            }else {
//                return Response.<User>ok().setMessage("Error Insert.");
//            }
//        }catch (UserNotFoundException e){
//            System.out.println("This Is Error :"+e);
//            return Response.<User>exception().setMessage("Insert Value Fail.").setSuccess(false);
//        }
//    }
    @DeleteMapping("/remove/{id}")
    Response<User> removeUser(@PathVariable("id")Integer id){
         try {
             User user = userService.findUserById(id);
             System.out.println(user);
             int result= userService.removeuser(id);
             System.out.println("this result :"+result);

             if(result>0) {
                 return Response.<User>deleteSuccess().setPayload(user).setMessage("One Row Is Delete.");
             }else {
                 return Response.<User>notFound().setMessage("Not found user by id :"+id);
             }
         }catch (Exception e){
             System.out.println("this is error :"+e);
             return Response.<User>exception().setMessage("Remove Value Fail.").setSuccess(false);
         }

    }
    @PutMapping("/update/{id}")
    Response<User> updateUser(@RequestBody UserRequest user,@PathVariable("id") Integer id){
        try {
            int updateRow= userService.updateUser(user,id);
            System.out.println(user);
            if (updateRow>0) {
                User newUser = new User().setUsername(user.getUsername())
                        .setGender(user.getGender())
                        .setAddress(user.getAddress())
                        .setId(id);
                return Response.<User>updateSuccess().setPayload(newUser).setMessage("You are update successfully.");
            }else {
                return Response.<User>notFound().setMessage("Cann't update ! user by id :"+ id).setSuccess(false);
            }
        }catch (Exception e){
            System.out.println("this is error :"+e);
            return Response.<User>exception().setMessage("Error Exception!!" +e).setSuccess(false);
        }
    }
    @GetMapping("/alluseraccount")

    public Response<List<UserAccount>> getALlUserAccount(){
        try {
            List<UserAccount> data= userService.getAllUserAccount();
            return Response.<List<UserAccount>>ok().setPayload(data).setMessage("success access data.");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return Response.<List<UserAccount>>exception().setMessage("Data get failed.").setSuccess(false);
        }
    }
    @GetMapping("")
   List<User> findByUserName(@RequestParam String username){
         return userService.findUserByName(username);
        }


        @GetMapping("/{field}")
      private Response<List<User>> findAllByUser(@PathVariable String field){
        List<User> userList =userService.findAllUserBySorted(field);
        return Response.<List<User>>ok().setPayload(userList).setMessage("ok");
        }

    @GetMapping("/pagination")
    Response<List<User>> paginationPageBySQl(@RequestParam(required = false,name = "limit",defaultValue = "5")int limit,
                                             @RequestParam(required = false,name = "page",defaultValue = "1")int page){
        int pages=((limit+1)-1)*page;
        List<User> userList = userService.paginationPageBySQLCode(limit,pages);
     return    Response.<List<User>>ok().setPayload(userList).setMessage("This is pagination page limit.");
    }
}
