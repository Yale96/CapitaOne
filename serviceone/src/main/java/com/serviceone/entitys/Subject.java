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
    
    @ManyToMany
    private List<User> followers;
    
    public Subject()
    {
        followers = new ArrayList<>();
    }
    
    public Subject(String naam, String omschrijving)
    {
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
