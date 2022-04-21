package com.example.restblog.web;

import com.example.restblog.data.CategoriesRepository;

public class CategoriesController {

    private final CategoriesRepository cr;

    public CategoriesController(CategoriesRepository cr) {
        this.cr = cr;
    }
}
