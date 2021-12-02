/*package com.ttn.project2.controller;

import com.ttn.project2.ExceptionHandler.ResourceNotFoundException;
import com.ttn.project2.Model.Products;
import com.ttn.project2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class ProductController {

    @Autowired
    ProductService productService;

    //API to Add Product
    @PostMapping("/add/product")
    public String addProduct(Products product){
        return productService.addProduct(product);

    }

    //API to Get Product Details
    @GetMapping("/view/product/{id}")
    public List<Products> viewProduct(@PathVariable long id)throws ResourceNotFoundException {
        return productService.viewProduct(id);
    }

    //API to delete Product
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }


    //@Secured("ROLE_SELLER")
    @PostMapping("/seller/products")
    @ResponseStatus(value= HttpStatus.OK)
    public ResponseEntity addNewProduct(@Valid @RequestBody Products product) {
        return productService.addNewProduct(product);
    }


}*/

