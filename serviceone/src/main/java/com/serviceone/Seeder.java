/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone;

import com.serviceone.entitys.User;
import com.serviceone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author yanni
 */
@Component
public class Seeder implements ApplicationRunner{
    
    private UserRepository userRepository;
    
    @Autowired
    public Seeder(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments aa) {
        User yannick = new User("Yannick", "yannickvanleeuwen@i-lion.nl", "Yannick", 21);
        User dennis = new User("Dennis", "dennisvanleeuwen@i-lion.nl", "Dennis", 19);
        User max = new User("Max", "maxvanleeuwen@i-lion.nl", "Max", 15);
        userRepository.save(yannick);
        userRepository.save(dennis);
        userRepository.save(max);
    }
    
}
