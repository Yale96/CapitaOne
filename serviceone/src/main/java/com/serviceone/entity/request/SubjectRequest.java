/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.entity.request;

import com.serviceone.entitys.Subject;
import java.util.ArrayList;
import java.util.List;

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
}
