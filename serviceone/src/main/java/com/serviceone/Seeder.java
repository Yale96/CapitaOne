/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone;

import com.serviceone.entitys.News;
import com.serviceone.entitys.Subject;
import com.serviceone.entitys.User;
import com.serviceone.repository.NewsRepository;
import com.serviceone.repository.SubjectRepository;
import com.serviceone.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
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
    private NewsRepository newsRepository;
    private SubjectRepository subjectRepository;
    
    @Autowired
    public Seeder(UserRepository userRepository, NewsRepository newsRepository, SubjectRepository subjectRepository)
    {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void run(ApplicationArguments aa) {
        Subject subjectOne = new Subject("Een", "EersteSubject");
        Subject subjectTwo = new Subject("Twee", "Tweedesubject");
        Subject subjectThree = new Subject("Drie", "Derdesubject");
        subjectRepository.save(subjectOne);
        subjectRepository.save(subjectTwo);
        subjectRepository.save(subjectThree);
        
        News newsOne = new News("Nieuwsbericht 1", 18, subjectOne);
        News newsTwo = new News("Nieuwsbericht 2", 5, subjectTwo);
        News newsThree = new News("Nieuwsbericht 3", 10, subjectThree);
        newsRepository.save(newsOne);
        newsRepository.save(newsTwo);
        newsRepository.save(newsThree);
        
        User yannick = new User("Yannick", "yannickvanleeuwen@i-lion.nl", "Yannick", 21);
        User dennis = new User("Dennis", "dennisvanleeuwen@i-lion.nl", "Dennis", 19);
        User max = new User("Max", "maxvanleeuwen@i-lion.nl", "Max", 15);
        
        List<News> newsYannick = new ArrayList<News>();
        List<News> newsDennis = new ArrayList<News>();
        List<News> newsMax = new ArrayList<News>();
        
        newsYannick.add(newsOne);
        newsDennis.add(newsTwo);
        newsMax.add(newsThree);
        
        yannick.setFollowingNews(newsYannick);
        dennis.setFollowingNews(newsDennis);
        max.setFollowingNews(newsMax);
        
        userRepository.save(yannick);
        userRepository.save(dennis);
        userRepository.save(max);
    }
    
}
