/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Yannick van Leeuwen
 */
@Entity
@Table(name = "app_subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    
    @NotNull
    private String naam;
    
    @NotNull
    private String omschrijving;
    
    @NotNull
    private int ageLimit;
    
    @ManyToMany
    private List<User> followers;
    
    public Subject()
    {
        followers = new ArrayList<>();
    }
    
    public Subject(String naam, String omschrijving, int age)
    {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.ageLimit = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
    
    
    
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}
