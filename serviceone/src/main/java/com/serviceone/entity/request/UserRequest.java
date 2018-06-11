/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.entity.request;

import com.serviceone.entitys.Subject;
import com.serviceone.entitys.User;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Yannick van Leeuwen
 */
public class UserRequest {
    
    public List<User> users;
    
    public UserRequest()
    {
        users = new ArrayList<User>();
    }
    
    public void addUser(String name, String mail, String password, int age)
    {
        User u = new User(name, mail, password, age);
        users.add(u);
    }
    
    public User findUser(String mail, String password)
    {
        User u = new User();
        for(User user: users)
        {
            if(user.getMail().equals(mail) && user.getPassword().equals(password))
            {
                u = user;
            }
        }
        return u;
    }
    
    public boolean login(String mail, String password)
    {
        if(findUser(mail, password) != null)
        {
            return true;
        }
        return false;
    }

    public List<Subject> getAllSubjectsPerUserFromThree(String name) {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> subjectsMap = restTemplate.getForObject("http://localhost:8094/users/getAllSubsByName?naam=" + name, List.class);

        List<Subject> returnList = new ArrayList<>();

        if (subjectsMap != null) {
            for (LinkedHashMap<String, Object> map : subjectsMap) {
                System.out.println("Subject : naam=" + map.get("naam"));
                String omschrijving = map.get("omschrijving").toString();
                String naam = map.get("naam").toString();
                int ageLimit = (int) map.get("ageLimit");
                Subject s = new Subject(naam, omschrijving, ageLimit);
                returnList.add(s);
            }
        } else {
            System.out.println("No user exist----------");
        }
        return returnList;
    }
}
