/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.controller;

import com.serviceone.entitys.News;
import com.serviceone.entitys.User;
import com.serviceone.repository.NewsRepository;
import com.serviceone.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yannick van Leeuwen
 */
@RestController
@RequestMapping("users")
public class UserController {

    private UserRepository userRepository;
    private NewsRepository newsRepository;

    @Autowired
    public UserController(UserRepository userRepository, NewsRepository newsRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
    }
    
    // TEST URL: http://localhost:8090/users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    //TEST URL: http://localhost:8090/users/login?name=Yannick&password=Yannick
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestParam("mail") String mail, @RequestParam("password") String password) {
        return userRepository.login(mail, password) != null;
    }
    
    //TEST URL: http://localhost:8090/users/register?name=test&mail=test@test.nl&password=test&age=21
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("password") String password, @RequestParam("age") int age) {
        User u = new User();
        u.setName(name);
        u.setMail(mail);
        u.setPassword(password);
        u.setAge(age);
        userRepository.save(u);
        return u;
    }
    
//     //TEST URL: http://localhost:8090/users/addNewsToUser?name=Yannick&subject=Tweede
//    @RequestMapping(value = "/addNewsToUser", method = RequestMethod.POST)
//    public User subscribeToNews(@RequestParam("name") String name, @RequestParam("subject") String subject) {
//        User u = userRepository.findByName(name);
//        News n = newsRepository.findNewsBySubject(subject);
//        return userRepository.insertNews(n);
//    }
}
