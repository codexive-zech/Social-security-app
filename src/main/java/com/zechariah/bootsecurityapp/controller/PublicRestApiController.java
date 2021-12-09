package com.zechariah.bootsecurityapp.controller;


import com.zechariah.bootsecurityapp.db.UserRepository;
import com.zechariah.bootsecurityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class PublicRestApiController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("test1")
    public String test1(){
        return "API Test 1, Admin & Manager";
    }

    @GetMapping("test2")
    public String test2(){
        return "API Test 2, Admin";
    }

    @GetMapping("test")
    public String test(){
        return "This is Testing.";
    }

    @GetMapping("report")
    public String report(){
        return "Some Data Report.";
    }

    @GetMapping("admin/users")
    public List<User> getAll(){
        return userRepository.findAll();
    }
}
