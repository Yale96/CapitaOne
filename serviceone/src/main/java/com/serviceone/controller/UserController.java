/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.controller;

import com.serviceone.entitys.Subject;
import com.serviceone.entitys.User;
import com.serviceone.repository.SubjectRepository;
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
    private SubjectRepository subjectRepository;

    @Autowired
    public UserController(UserRepository userRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
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
    
     //TEST URL: http://localhost:8090/users/addSubjectToUser?name=Yannick&subject=Twee
    @RequestMapping(value = "/addSubjectToUser", method = RequestMethod.POST)
    public User subscribeToNews(@RequestParam("name") String name, @RequestParam("subject") String subject) {
        User u = userRepository.findOne(userRepository.findByName(name).getId());
        Subject s = subjectRepository.findOne(subjectRepository.findByName(subject).getId());
        u.addSubject(s);
        userRepository.save(u);
        String ss = "Debug";
        return u;
    }
    
    // TEST URL: http://localhost:8090/users/getAllByName?naam=Yannick
    @RequestMapping(value = "/getAllByName", method = RequestMethod.GET)
    public List<Subject> getAllFromOneByName(@RequestParam("naam") String naam) {
        return userRepository.getAllSubjectsPerUserFromOne(naam);
    }
    
    
}
