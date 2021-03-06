/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.controller;

import com.serviceone.entity.request.SubjectRequest;
import com.serviceone.entitys.Subject;
import com.serviceone.repository.SubjectRepository;
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
@RequestMapping("subjects")
public class SubjectController {
    
    private SubjectRepository subjectRepository;
    private SubjectRequest subjectRequest;
    
    @Autowired
    public SubjectController(SubjectRepository subjectController){
        this.subjectRepository = subjectController;
        subjectRequest = new SubjectRequest();
    }
    
    // TEST URL: http://localhost:8090/subjects
    @RequestMapping(method = RequestMethod.GET)
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }
    
    // TEST URL: http://localhost:8090/subjects/refresh
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public List<Subject> refreshSubjects() {
        List<Subject> getAll = subjectRequest.getAllFromThree();
        for (Subject s : getAll) {
                Subject toSave = subjectRepository.findOne(subjectRepository.findSingleSubjectByNaam(s.getNaam()).getId());
                if(toSave == null)
                {
                    Subject sub = new Subject(s.getNaam(), s.getOmschrijving(), s.getAgeLimit());
                    subjectRepository.save(sub);
                }
                else
                    subjectRepository.save(toSave);
            }
        return subjectRepository.findAll();
    }
    
}
