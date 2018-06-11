/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.entity.request;

import com.serviceone.entitys.Subject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Yannick van Leeuwen
 */
public class SubjectRequest {
    
    public List<Subject> subjects;
    
    public SubjectRequest()
    {
        subjects = new ArrayList<>();
    }
    
    public void addSubject(String naam, String omschrijving, int age)
    {
        Subject s = new Subject(naam, omschrijving, age);
        subjects.add(s);
    }
    
    public List<Subject> getAllSubjects()
    {
        List<Subject> returnList = new ArrayList<>();
        for(Subject s: subjects)
        {
            returnList.add(s);
        }
        return returnList;
    }

    public List<Subject> getAllFromThree() {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> subjectsMap = restTemplate.getForObject("http://localhost:8094/subjects/getAll", List.class);
        
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
