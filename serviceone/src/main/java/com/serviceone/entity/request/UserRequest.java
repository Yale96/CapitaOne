/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.entity.request;

import com.serviceone.entitys.User;
import java.util.ArrayList;
import java.util.List;

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
    
    public void createData()
    {
        
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
}
