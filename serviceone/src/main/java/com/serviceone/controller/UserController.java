/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.controller;

import com.serviceone.entity.request.UserRequest;
import com.serviceone.entitys.Subject;
import com.serviceone.entitys.User;
import com.serviceone.repository.SubjectRepository;
import com.serviceone.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yannick van Leeuwen
 */
@RestController
 @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("users")
public class UserController {

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;
    private UserRequest userRequest;

    @Autowired
    public UserController(UserRepository userRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        userRequest = new UserRequest();
    }
    
    // TEST URL: http://localhost:8090/users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    //TEST URL: http://localhost:8090/users/login?name=Yannick&password=Yannick
    //@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestParam("name") String name, @RequestParam("password") String password) {
        return userRepository.login(name, password) != null;
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
    
    //TEST URL: http://localhost:8090/users/removeSubjectFromUser?name=Yannick&subject=Twee
    @RequestMapping(value = "/removeSubjectFromUser", method = RequestMethod.POST)
    public User unsubscribeToNews(@RequestParam("name") String name, @RequestParam("subject") String subject) {
        User u = userRepository.findOne(userRepository.findByName(name).getId());
        Subject s = subjectRepository.findOne(subjectRepository.findByName(subject).getId());
        u.removeSubject(s);
        userRepository.save(u);
        String ss = "Debug";
        return u;
    }
    
    // TEST URL: http://localhost:8090/users/getAllByName?naam=Yannick
    @RequestMapping(value = "/getAllByName", method = RequestMethod.GET)
    public List<Subject> getAllFromOneByName(@RequestParam("naam") String naam) {
        return userRepository.getAllSubjectsPerUserFromOne(naam);
    }
    
     // TEST URL: http://localhost:8090/users/getNotFollowing?naam=Yannick
    @RequestMapping(value = "/getNotFollowing", method = RequestMethod.GET)
    public List<Subject> getNotFollowing(@RequestParam("naam") String naam) {
        List<Subject> returnList = new ArrayList<>();
        List<Subject> userSubjects = new ArrayList<>();
        User u = userRepository.getOne(userRepository.findByName(naam).getId());
        for(Subject s: subjectRepository.findAll())
        {
            for(Subject ss: u.getFollowingSubjects())
            {
                if(! s.getNaam().equals(ss.getNaam()))
                {
                    returnList.add(s);
                }
            }
        }
        return returnList;
    }
    
    // TEST URL: http://localhost:8090/users/refresh
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public User refreshFollowingSubjects(@RequestParam("naam") String naam) {
        User u = userRepository.findOne(userRepository.findByName(naam).getId());
        List<Subject> getAllPerUser = userRequest.getAllSubjectsPerUserFromThree(naam);
        List<Subject> savedObjects = new ArrayList<>();
        for (Subject s : getAllPerUser) {
            for (Subject subject : subjectRepository.findSubjectByNaam(s.getNaam())) {
                Subject toSave = subjectRepository.findOne(subject.getId());
                subjectRepository.save(toSave);
                savedObjects.add(toSave);
            }
        }
        u.setFollowingSubjects(savedObjects);
        userRepository.save(u);
        return u;
    }
    
}
