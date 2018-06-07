/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.repository;

import com.serviceone.entitys.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yannick van Leeuwen
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
    @Query("SELECT s FROM Subject s WHERE LOWER(s.naam) = LOWER(:naam)")
    public Subject findByName(@Param("naam") String name);
}
