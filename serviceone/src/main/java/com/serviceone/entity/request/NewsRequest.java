/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceone.entity.request;

import com.serviceone.entitys.News;
import com.serviceone.entitys.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yannick van Leeuwen
 */
public class NewsRequest {
    public List<News> articles;
    
    public NewsRequest()
    {
        articles = new ArrayList<>();
    }
    
    public void addNews(String content, int ageLimit, Subject subject)
    {
        News n = new News(content, ageLimit, subject);
        articles.add(n);
    }
    
    public List<News> getAllNews()
    {
        List<News> returnList = new ArrayList<>();
        for(News n: articles)
        {
            returnList.add(n);
        }
        return returnList;
    }
    
    public News findBySubject(String subject)
    {
        News news = new News();
        for(News n: articles)
        {
            if(n.getSubject().equals(subject))
            {
                news = n;
            }
        }
        return news;
    }
}
