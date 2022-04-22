package com.example.restblog.web;

import com.example.restblog.data.CategoriesRepository;
import com.example.restblog.data.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public class CategoriesController {

    private final CategoriesRepository cr;

    public CategoriesController(CategoriesRepository cr) {
        this.cr = cr;
    }

}
