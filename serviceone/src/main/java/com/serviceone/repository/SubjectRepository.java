/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.repository;

import com.serviceone.entitys.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Yannick van Leeuwen
 */
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
}
