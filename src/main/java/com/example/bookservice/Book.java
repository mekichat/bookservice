package com.example.bookservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private String writer;
    private String type;
    private int published;    
    //private String language;

    public Integer getId() {
        return id;
      }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String s)
    {
        title  = s;
    }
    public String getTitle()
    {
        return title;
    }

    public void setWriter(String s)
    {
        writer  = s;
    }
    public String getWriter()
    {
        return writer;
    }

    public void setType(String s)
    {
        type  = s;
    }
    public String getType()
    {
        return type;
    }    

    public Integer getPublished() {
        return published;
      }
    
    public void setPublished(Integer year) {
        published = year;
    }

    /* public void setLanguage(String s)
    {
        language  = s;
    }
    public String getLanguage()
    {
        return language;
    } */


    
}

