/*package com.ttn.project2.controller;


import com.ttn.project2.Model.CategoryCo;
import com.ttn.project2.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity addNewCategory(@Valid @RequestBody CategoryCo co) {
        return categoryService.addNewCategory(co);
    }
}*/
