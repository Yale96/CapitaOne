/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yannick van Leeuwen
 */
@RestController
@RequestMapping("subjects")
public class SubjectController {
    
    private SubjectController subjectController;
    
    @Autowired
    public SubjectController(SubjectController subjectController){
        this.subjectController = subjectController;
    }
}
